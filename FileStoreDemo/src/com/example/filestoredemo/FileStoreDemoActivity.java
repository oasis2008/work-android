package com.example.filestoredemo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FileStoreDemoActivity extends Activity {
	EditText mEdit;
	Button mSave;
	Button mRead;
	TextView mTextView;
	String fn = "myandroid.log";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_store_demo);
		mEdit = (EditText) findViewById(R.id.editbox);
		mSave = (Button) findViewById(R.id.save);
		mRead = (Button) findViewById(R.id.read);
		mTextView = (TextView) findViewById(R.id.file);
		
		mSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				save();
			}
		});
		
		mRead.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				load();
			}
		});
	}

	void save() {
		try {
			FileOutputStream fos = this
					.openFileOutput(fn, Context.MODE_PRIVATE);
			fos.write(mEdit.getText().toString().getBytes());
			fos.close();
			Toast.makeText(FileStoreDemoActivity.this, "Saved",
					Toast.LENGTH_SHORT).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void load() {
		try {
			FileInputStream fis = this.openFileInput(fn);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				stream.write(buffer, 0, length);
			}
			stream.close();
			fis.close();
			mTextView.setText(stream.toString());
			Toast.makeText(FileStoreDemoActivity.this, "Loaded",
					Toast.LENGTH_SHORT).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_file_store_demo, menu);
		return true;
	}
}
