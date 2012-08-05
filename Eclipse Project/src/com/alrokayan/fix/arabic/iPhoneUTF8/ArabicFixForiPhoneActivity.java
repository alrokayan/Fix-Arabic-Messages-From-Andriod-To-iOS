/*
 * Copyright 2011,2012 Mohammed Alrokayan
 *  
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
*/


package com.alrokayan.fix.arabic.iPhoneUTF8;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.app.*;
import android.content.*;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.ClipboardManager;
import android.view.*;
import android.widget.*;
import android.util.Log;

public class ArabicFixForiPhoneActivity extends Activity implements Runnable {
	EditText ArabicString = null;
	LinearLayout MainLayout = null;
	LinearLayout TextLinearLayout = null;
	LinearLayout ButtonLinearLayout = null;
	Button mButton_Convert = null;
	Button mButton_Clear_n_Past = null;

	String CorrectionForiPhone = "";
	String Squares = "";

	String UnSupportedHexs = " ";
	ArrayList<Character> UnSupportedChars = new ArrayList<Character>();
	int UnSupportedCharsCount = 0;

	Context c = this;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MainLayout = new LinearLayout(this);
		TextLinearLayout = new LinearLayout(this);
		ButtonLinearLayout = new LinearLayout(this);

		ArabicString = new EditText(this);
		ArabicString.setText("");
		ArabicString.setGravity(Gravity.TOP);
		ArabicString.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT,1));
		TextLinearLayout.addView(ArabicString);

		mButton_Convert = new Button(this);
		mButton_Convert.setText("تصحيح ونسخ");
		mButton_Convert.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT,1));
		ButtonLinearLayout.addView(mButton_Convert);
		mButton_Convert.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					convert();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		});

		mButton_Clear_n_Past = new Button(this);
		mButton_Clear_n_Past.setText("مسح المحتوى ثم لصق");
		mButton_Clear_n_Past.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT,1));
		ButtonLinearLayout.addView(mButton_Clear_n_Past);
		mButton_Clear_n_Past.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				ArabicString.setText(clipboard.getText());

				Context context = getApplicationContext();
				CharSequence text = "تم اللصق";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		});


		MainLayout.setOrientation(LinearLayout.VERTICAL);
		TextLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT,1));
		MainLayout.addView(ButtonLinearLayout);
		MainLayout.addView(TextLinearLayout);
		setContentView(MainLayout);
	}

	private ProgressDialog pd;

	public void convert() throws UnsupportedEncodingException{

		pd = ProgressDialog.show(this, "جاري التصحيح ...", "الرجاء الإنتظار..", true, false);

		Squares = ArabicString.getText().toString();

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		Log.d("convert function", "BEFORE: "+Squares);
		Log.d("convert function", getUTF8(Squares));

		CorrectionForiPhone = "";

		for(int i=0; i< Squares.length(); i++)
		{
			String UTF8Hex = UnicodeFormatter.charToHex(Squares.charAt(i));

			if(UTF8Hex.startsWith("FE"))
			{
				if(UTF8Hex.charAt(2) == '8')
				{
					if(UTF8Hex.charAt(3) == '0')
					{CorrectionForiPhone+="\u0621"; continue;}
					
					if(UTF8Hex.charAt(3) == '1')
					{CorrectionForiPhone+="\u0622"; continue;}
					
					if(UTF8Hex.charAt(3) == '2')
					{CorrectionForiPhone+="\u0622"; continue;}
					
					if(UTF8Hex.charAt(3) == '3')
					{CorrectionForiPhone+="\u0623"; continue;}
					
					if(UTF8Hex.charAt(3) == '4')
					{CorrectionForiPhone+="\u0623"; continue;}
					
					if(UTF8Hex.charAt(3) == '5')
					{CorrectionForiPhone+="\u0624"; continue;}
					
					if(UTF8Hex.charAt(3) == '6')
					{CorrectionForiPhone+="\u0624"; continue;}
					
					if(UTF8Hex.charAt(3) == '7')
					{CorrectionForiPhone+="\u0625"; continue;}
					
					if(UTF8Hex.charAt(3) == '8')
					{CorrectionForiPhone+="\u0625"; continue;}
					
					if(UTF8Hex.charAt(3) == '9')
					{CorrectionForiPhone+="\u0626"; continue;}
					
					if(UTF8Hex.charAt(3) == 'A')
					{CorrectionForiPhone+="\u0626"; continue;}
					
					if(UTF8Hex.charAt(3) == 'B')
					{CorrectionForiPhone+="\u0626"; continue;}
					
					if(UTF8Hex.charAt(3) == 'C')
					{CorrectionForiPhone+="\u0626"; continue;}
					
					if(UTF8Hex.charAt(3) == 'D')
					{CorrectionForiPhone+="\u0627"; continue;}
					
					if(UTF8Hex.charAt(3) == 'E')
					{CorrectionForiPhone+="\u0627"; continue;}
					
					if(UTF8Hex.charAt(3) == 'F')
					{CorrectionForiPhone+="\u0628"; continue;}
				}
				
				if(UTF8Hex.charAt(2) == '9')
				{
					if(UTF8Hex.charAt(3) == '0')
					{CorrectionForiPhone+="\u0628"; continue;}
					
					if(UTF8Hex.charAt(3) == '1')
					{CorrectionForiPhone+="\u0628"; continue;}
					
					if(UTF8Hex.charAt(3) == '2')
					{CorrectionForiPhone+="\u0628"; continue;}
					
					if(UTF8Hex.charAt(3) == '3')
					{CorrectionForiPhone+="\u0629"; continue;}
					
					if(UTF8Hex.charAt(3) == '4')
					{CorrectionForiPhone+="\u0629"; continue;}
					
					if(UTF8Hex.charAt(3) == '5')
					{CorrectionForiPhone+="\u062A"; continue;}
					
					if(UTF8Hex.charAt(3) == '6')
					{CorrectionForiPhone+="\u062A"; continue;}
					
					if(UTF8Hex.charAt(3) == '7')
					{CorrectionForiPhone+="\u062A"; continue;}
					
					if(UTF8Hex.charAt(3) == '8')
					{CorrectionForiPhone+="\u062A"; continue;}
					
					if(UTF8Hex.charAt(3) == '9')
					{CorrectionForiPhone+="\u062B"; continue;}
					
					if(UTF8Hex.charAt(3) == 'A')
					{CorrectionForiPhone+="\u062B"; continue;}
					
					if(UTF8Hex.charAt(3) == 'B')
					{CorrectionForiPhone+="\u062B"; continue;}
					
					if(UTF8Hex.charAt(3) == 'C')
					{CorrectionForiPhone+="\u062B"; continue;}
					
					if(UTF8Hex.charAt(3) == 'D')
					{CorrectionForiPhone+="\u062C"; continue;}
					
					if(UTF8Hex.charAt(3) == 'E')
					{CorrectionForiPhone+="\u062C"; continue;}
					
					if(UTF8Hex.charAt(3) == 'F')
					{CorrectionForiPhone+="\u062C"; continue;}
				}
				
				if(UTF8Hex.charAt(2) == 'A')
				{
					if(UTF8Hex.charAt(3) == '0')
					{CorrectionForiPhone+="\u062C"; continue;}
					
					if(UTF8Hex.charAt(3) == '1')
					{CorrectionForiPhone+="\u062D"; continue;}
					
					if(UTF8Hex.charAt(3) == '2')
					{CorrectionForiPhone+="\u062D"; continue;}
					
					if(UTF8Hex.charAt(3) == '3')
					{CorrectionForiPhone+="\u062D"; continue;}
					
					if(UTF8Hex.charAt(3) == '4')
					{CorrectionForiPhone+="\u062D"; continue;}
					
					if(UTF8Hex.charAt(3) == '5')
					{CorrectionForiPhone+="\u062E"; continue;}
					
					if(UTF8Hex.charAt(3) == '6')
					{CorrectionForiPhone+="\u062E"; continue;}
					
					if(UTF8Hex.charAt(3) == '7')
					{CorrectionForiPhone+="\u062E"; continue;}
					
					if(UTF8Hex.charAt(3) == '8')
					{CorrectionForiPhone+="\u062E"; continue;}
					
					if(UTF8Hex.charAt(3) == '9')
					{CorrectionForiPhone+="\u062F"; continue;}
					
					if(UTF8Hex.charAt(3) == 'A')
					{CorrectionForiPhone+="\u062F"; continue;}
					
					if(UTF8Hex.charAt(3) == 'B')
					{CorrectionForiPhone+="\u0630"; continue;}
					
					if(UTF8Hex.charAt(3) == 'C')
					{CorrectionForiPhone+="\u0630"; continue;}
					
					if(UTF8Hex.charAt(3) == 'D')
					{CorrectionForiPhone+="\u0631"; continue;}
					
					if(UTF8Hex.charAt(3) == 'E')
					{CorrectionForiPhone+="\u0631"; continue;}
					
					if(UTF8Hex.charAt(3) == 'F')
					{CorrectionForiPhone+="\u0632"; continue;}
				}
				
				if(UTF8Hex.charAt(2) == 'B')
				{
					if(UTF8Hex.charAt(3) == '0')
					{CorrectionForiPhone+="\u0632"; continue;}
					
					if(UTF8Hex.charAt(3) == '1')
					{CorrectionForiPhone+="\u0633"; continue;}
					
					if(UTF8Hex.charAt(3) == '2')
					{CorrectionForiPhone+="\u0633"; continue;}
					
					if(UTF8Hex.charAt(3) == '3')
					{CorrectionForiPhone+="\u0633"; continue;}
					
					if(UTF8Hex.charAt(3) == '4')
					{CorrectionForiPhone+="\u0633"; continue;}
					
					if(UTF8Hex.charAt(3) == '5')
					{CorrectionForiPhone+="\u0634"; continue;}
					
					if(UTF8Hex.charAt(3) == '6')
					{CorrectionForiPhone+="\u0634"; continue;}
					
					if(UTF8Hex.charAt(3) == '7')
					{CorrectionForiPhone+="\u0634"; continue;}
					
					if(UTF8Hex.charAt(3) == '8')
					{CorrectionForiPhone+="\u0634"; continue;}
					
					if(UTF8Hex.charAt(3) == '9')
					{CorrectionForiPhone+="\u0635"; continue;}
					
					if(UTF8Hex.charAt(3) == 'A')
					{CorrectionForiPhone+="\u0635"; continue;}
					
					if(UTF8Hex.charAt(3) == 'B')
					{CorrectionForiPhone+="\u0635"; continue;}
					
					if(UTF8Hex.charAt(3) == 'C')
					{CorrectionForiPhone+="\u0635"; continue;}
					
					if(UTF8Hex.charAt(3) == 'D')
					{CorrectionForiPhone+="\u0636"; continue;}
					
					if(UTF8Hex.charAt(3) == 'E')
					{CorrectionForiPhone+="\u0636"; continue;}
					
					if(UTF8Hex.charAt(3) == 'F')
					{CorrectionForiPhone+="\u0636"; continue;}
				}
				
				if(UTF8Hex.charAt(2) == 'C')
				{
					if(UTF8Hex.charAt(3) == '0')
					{CorrectionForiPhone+="\u0636"; continue;}
					
					if(UTF8Hex.charAt(3) == '1')
					{CorrectionForiPhone+="\u0637"; continue;}
					
					if(UTF8Hex.charAt(3) == '2')
					{CorrectionForiPhone+="\u0637"; continue;}
					
					if(UTF8Hex.charAt(3) == '3')
					{CorrectionForiPhone+="\u0637"; continue;}
					
					if(UTF8Hex.charAt(3) == '4')
					{CorrectionForiPhone+="\u0637"; continue;}
					
					if(UTF8Hex.charAt(3) == '5')
					{CorrectionForiPhone+="\u0638"; continue;}
					
					if(UTF8Hex.charAt(3) == '6')
					{CorrectionForiPhone+="\u0638"; continue;}
					
					if(UTF8Hex.charAt(3) == '7')
					{CorrectionForiPhone+="\u0638"; continue;}
					
					if(UTF8Hex.charAt(3) == '8')
					{CorrectionForiPhone+="\u0638"; continue;}
					
					if(UTF8Hex.charAt(3) == '9')
					{CorrectionForiPhone+="\u0639"; continue;}
					
					if(UTF8Hex.charAt(3) == 'A')
					{CorrectionForiPhone+="\u0639"; continue;}
					
					if(UTF8Hex.charAt(3) == 'B')
					{CorrectionForiPhone+="\u0639"; continue;}
					
					if(UTF8Hex.charAt(3) == 'C')
					{CorrectionForiPhone+="\u0639"; continue;}
					
					if(UTF8Hex.charAt(3) == 'D')
					{CorrectionForiPhone+="\u063A"; continue;}
					
					if(UTF8Hex.charAt(3) == 'E')
					{CorrectionForiPhone+="\u063A"; continue;}
					
					if(UTF8Hex.charAt(3) == 'F')
					{CorrectionForiPhone+="\u063A"; continue;}
				}
				
				if(UTF8Hex.charAt(2) == 'D')
				{
					if(UTF8Hex.charAt(3) == '0')
					{CorrectionForiPhone+="\u063A"; continue;}
					
					if(UTF8Hex.charAt(3) == '1')
					{CorrectionForiPhone+="\u0641"; continue;}
					
					if(UTF8Hex.charAt(3) == '2')
					{CorrectionForiPhone+="\u0641"; continue;}
					
					if(UTF8Hex.charAt(3) == '3')
					{CorrectionForiPhone+="\u0641"; continue;}
					
					if(UTF8Hex.charAt(3) == '4')
					{CorrectionForiPhone+="\u0641"; continue;}
					
					if(UTF8Hex.charAt(3) == '5')
					{CorrectionForiPhone+="\u0642"; continue;}
					
					if(UTF8Hex.charAt(3) == '6')
					{CorrectionForiPhone+="\u0642"; continue;}
					
					if(UTF8Hex.charAt(3) == '7')
					{CorrectionForiPhone+="\u0642"; continue;}
					
					if(UTF8Hex.charAt(3) == '8')
					{CorrectionForiPhone+="\u0642"; continue;}
					
					if(UTF8Hex.charAt(3) == '9')
					{CorrectionForiPhone+="\u0643"; continue;}
					
					if(UTF8Hex.charAt(3) == 'A')
					{CorrectionForiPhone+="\u0643"; continue;}
					
					if(UTF8Hex.charAt(3) == 'B')
					{CorrectionForiPhone+="\u0643"; continue;}
					
					if(UTF8Hex.charAt(3) == 'C')
					{CorrectionForiPhone+="\u0643"; continue;}
					
					if(UTF8Hex.charAt(3) == 'D')
					{CorrectionForiPhone+="\u0644"; continue;}
					
					if(UTF8Hex.charAt(3) == 'E')
					{CorrectionForiPhone+="\u0644"; continue;}
					
					if(UTF8Hex.charAt(3) == 'F')
					{CorrectionForiPhone+="\u0644"; continue;}
				}
				
				if(UTF8Hex.charAt(2) == 'E')
				{
					if(UTF8Hex.charAt(3) == '0')
					{CorrectionForiPhone+="\u0644"; continue;}
					
					if(UTF8Hex.charAt(3) == '1')
					{CorrectionForiPhone+="\u0645"; continue;}
					
					if(UTF8Hex.charAt(3) == '2')
					{CorrectionForiPhone+="\u0645"; continue;}
					
					if(UTF8Hex.charAt(3) == '3')
					{CorrectionForiPhone+="\u0645"; continue;}
					
					if(UTF8Hex.charAt(3) == '4')
					{CorrectionForiPhone+="\u0645"; continue;}
					
					if(UTF8Hex.charAt(3) == '5')
					{CorrectionForiPhone+="\u0646"; continue;}
					
					if(UTF8Hex.charAt(3) == '6')
					{CorrectionForiPhone+="\u0646"; continue;}
					
					if(UTF8Hex.charAt(3) == '7')
					{CorrectionForiPhone+="\u0646"; continue;}
					
					if(UTF8Hex.charAt(3) == '8')
					{CorrectionForiPhone+="\u0646"; continue;}
					
					if(UTF8Hex.charAt(3) == '9')
					{CorrectionForiPhone+="\u0647"; continue;}
					
					if(UTF8Hex.charAt(3) == 'A')
					{CorrectionForiPhone+="\u0647"; continue;}
					
					if(UTF8Hex.charAt(3) == 'B')
					{CorrectionForiPhone+="\u0647"; continue;}
					
					if(UTF8Hex.charAt(3) == 'C')
					{CorrectionForiPhone+="\u0647"; continue;}
					
					if(UTF8Hex.charAt(3) == 'D')
					{CorrectionForiPhone+="\u0648"; continue;}
					
					if(UTF8Hex.charAt(3) == 'E')
					{CorrectionForiPhone+="\u0648"; continue;}
					
					if(UTF8Hex.charAt(3) == 'F')
					{CorrectionForiPhone+="\u0649"; continue;}
				}
				
				if(UTF8Hex.charAt(2) == 'F')
				{
					if(UTF8Hex.charAt(3) == '0')
					{CorrectionForiPhone+="\u0649"; continue;}
					
					if(UTF8Hex.charAt(3) == '1')
					{CorrectionForiPhone+="\u064A"; continue;}
					
					if(UTF8Hex.charAt(3) == '2')
					{CorrectionForiPhone+="\u064A"; continue;}
					
					if(UTF8Hex.charAt(3) == '3')
					{CorrectionForiPhone+="\u064A"; continue;}
					
					if(UTF8Hex.charAt(3) == '4')
					{CorrectionForiPhone+="\u064A"; continue;}
					
					if(UTF8Hex.charAt(3) == '5')
					{CorrectionForiPhone+="\u0644"+"\u0622"; continue;}
					
					if(UTF8Hex.charAt(3) == '6')
					{CorrectionForiPhone+="\u0644"+"\u0622"; continue;}
					
					if(UTF8Hex.charAt(3) == '7')
					{CorrectionForiPhone+="\u0644"+"\u0623"; continue;}
					
					if(UTF8Hex.charAt(3) == '8')
					{CorrectionForiPhone+="\u0644"+"\u0623"; continue;}
					
					if(UTF8Hex.charAt(3) == '9')
					{CorrectionForiPhone+="\u0644"+"\u0625"; continue;}
					
					if(UTF8Hex.charAt(3) == 'A')
					{CorrectionForiPhone+="\u0644"+"\u0625"; continue;}
					
					if(UTF8Hex.charAt(3) == 'B')
					{CorrectionForiPhone+="\u0644"+"\u0627"; continue;}
					
					if(UTF8Hex.charAt(3) == 'C')
					{CorrectionForiPhone+="\u0644"+"\u0627"; continue;}
				}
				
			}
			

			// Restore preferences
			SharedPreferences settings = getPreferences(0);
			Boolean ShowMessage = settings.getBoolean("ShowMessage", true);

			if(ShowMessage && UTF8Hex.compareToIgnoreCase("FB50")>0 && UTF8Hex.compareToIgnoreCase("FDFF")<0)
			{
				char UnSupportedChar = UnicodeFormatter.HexToChar(UTF8Hex);

				if(!UnSupportedChars.contains(UnSupportedChar))
				{
					UnSupportedHexs+=UTF8Hex+" ";
					UnSupportedChars.add(UnSupportedChar);
					UnSupportedCharsCount++;
				}
			}

			if(ShowMessage && UTF8Hex.compareToIgnoreCase("FE81")>0 && UTF8Hex.compareToIgnoreCase("FEFD")<0)
			{
				char UnSupportedChar = UnicodeFormatter.HexToChar(UTF8Hex);

				if(!UnSupportedChars.contains(UnSupportedChar))
				{
					UnSupportedHexs+=UTF8Hex+" ";
					UnSupportedChars.add(UnSupportedChar);
					UnSupportedCharsCount++;
				}
			}


			CorrectionForiPhone+=Squares.charAt(i);
		}

		Log.d("convert function", "UnSupportedCharsCount: "+UnSupportedCharsCount);
		Log.d("convert function", "UnSupportedHexs: "+UnSupportedHexs);
		Log.d("convert function", "UnSupportedChars: "+UnSupportedChars);
		Log.d("convert function", "AFTER: "+CorrectionForiPhone);
		Log.d("convert function", getUTF8(CorrectionForiPhone));


		ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		clipboard.setText(CorrectionForiPhone);

		handler.sendEmptyMessage(0);

		Log.d("convert function", "Your Text WAS: "+Squares+"\nAnd Now Become: "+CorrectionForiPhone);
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			pd.dismiss();
			CharSequence text = "تم النسخ";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(c, text, duration);
			toast.show();
			ArabicString.setText(CorrectionForiPhone);

			// Restore preferences
			SharedPreferences settings = getPreferences(0);
			Boolean ShowMessage = settings.getBoolean("ShowMessage", true);

			if(UnSupportedCharsCount>0 && ShowMessage)
			{
				String UnSupportedCharsString = "";
				for (char UnSupportedChar : UnSupportedChars) {
					UnSupportedCharsString+= " "+UnSupportedChar;
				}
				String Message="تم النسخ، لكن هناك بعض الحروف غير مدعومه .. هل تود إبلاغ المبرمج عن هذه الحروف ليتم تصحيحها في الإصدار القادم؟ ";
				final String ToDevMessage = "Version: 1.4\nUnSupportedCharsCount: "+UnSupportedCharsCount+"\nUnSupportedHexs: "+UnSupportedHexs+"\nUnSupportedCharsString: "+UnSupportedCharsString;

				new AlertDialog.Builder(c)
				.setTitle("خطأ بسيط")
				.setMessage(Message)
				.setPositiveButton("لا تزعجنا بهالرساله", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 
						// We need an Editor object to make preference changes.
						// All objects are from android.context.Context
						SharedPreferences settings = getPreferences(0);
						SharedPreferences.Editor editor = settings.edit();
						editor.putBoolean("ShowMessage", false);

						// Commit the edits!
						editor.commit();

						CharSequence text = "خلاص ماراح تشوف هالرساله";
						int duration = Toast.LENGTH_SHORT;
						Toast toast = Toast.makeText(c, text, duration);
						toast.show();
					}
				})
				.setNeutralButton("لاحقا", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 
						//Nothing
					}
				})
				.setNegativeButton("نعم", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 
						Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND); 
						String aEmailList[] = { "support@alrokayan.com" };
						emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
						emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Andriod Arabic Fix - v1.4");
						emailIntent.setType("plain/text");
						emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, ToDevMessage);
						startActivity(Intent.createChooser(emailIntent, "إرسال إيميل عن طريق:"));
					}
				})

				.show();
			}

		}
	};

	public static String getUTF8(String string) {
		String result = "";
		for (int k = 0; k < string.length(); k++) {
			String subresult = "Char: " + string.charAt(k) + " UTF-8 HEX: " + UnicodeFormatter.charToHex(string.charAt(k));
			System.out.println(subresult);
			result+=subresult;
		}
		return result;
	}
}


class UnicodeFormatter {
	static public String charToHex(char c) {
		// Returns hex String representation of char c
		byte hi = (byte) (c >>> 8);
		byte lo = (byte) (c & 0xff);
		return byteToHex(hi) + byteToHex(lo);
	}

	static public String byteToHex(byte b) {
		// Returns hex String representation of byte b
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
		return new String(array);
	}

	static public char HexToChar(String hex)
	{
		Log.d("convert function", "HEX: "+hex);
		int i= Integer.parseInt(hex,16);
		Log.d("convert function", "Decimal: "+i);
		char c = (char)i;
		Log.d("convert function", "Char is: "+c);
		return c;
	}

} // class
