package fu.example.currencyconverter;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.Currency;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.database.CursorWrapper;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private double USD = 1;// standard currency
	private double VND = 21276.5957;// 1 USD
	private double ERU = 1.3531;// 1 USD
	private Button btConvert = null;
	private Spinner sp1, sp2;
	private TextView tvTo;
	private EditText edFrom;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loadLayout();

	}

	private void loadLayout() {
		sp1 = (Spinner) findViewById(R.id.spinner1);
		String[] items1 = new String[] { "USD", "VND", "ERU" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_spinner_dropdown_item, items1);
		sp1.setAdapter(adapter);

		sp2 = (Spinner) findViewById(R.id.spinner2);
		String[] items2 = new String[] { "USD", "VND", "ERU" };
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_spinner_dropdown_item, items2);
		sp2.setAdapter(adapter2);

		btConvert = (Button) findViewById(R.id.button1);
		btConvert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				convert();
			}
		});

		edFrom = (EditText) findViewById(R.id.editText1);

		tvTo = (TextView) findViewById(R.id.textView3);

	}

	private double calExchange(String FromCurrency, String ToCurrency) {
		double from = 0;
		if (FromCurrency.compareTo("USD") == 0) {
			from = USD;
		} else if (FromCurrency.compareTo("ERU") == 0) {
			from = ERU;
		} else {
			from = VND;
		}

		double to = 0;
		if (ToCurrency.compareTo("USD") == 0) {
			to = USD;
		} else if (ToCurrency.compareTo("ERU") == 0) {
			to = ERU;
		} else {
			to = VND;
		}

		return (to / from);
	}

	private void convert() {
		String from = sp1.getSelectedItem().toString();
		String to = sp2.getSelectedItem().toString();

		Double exchange = calExchange(from, to);
		String input = edFrom.getText().toString();
		Double fromvalue = Double.parseDouble(input);
		fromvalue = fromvalue * exchange;
		DecimalFormat format = new DecimalFormat("###,###.##");
		tvTo.setText(format.format(Double.parseDouble(input)) + " " + from
				+ "\n = \n" + format.format(fromvalue) + " " + to);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
