package fu.example.currencyconverter;

import java.util.Currency;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.database.CursorWrapper;
import android.graphics.Color;
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
	private double VND = 20000;// 1 USD
	private double EUR = 0.8;// 1 USD
	private Button btConvert = null;
	private Spinner sp1, sp2;
	private TextView tvTo;
	private EditText edFrom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loadLayout();
		// Currency USD = Currency.getInstance("USD");
		// Toast.makeText(getApplicationContext(),
		// Currency.getInstance(Locale.US).getSymbol(),
		// Toast.LENGTH_SHORT).show();
	}

	private void loadLayout() {
		sp1 = (Spinner) findViewById(R.id.spinner1);
		String[] items1 = new String[] { "USD", "VND", "EUR" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_spinner_dropdown_item, items1);
		sp1.setAdapter(adapter);

		sp2 = (Spinner) findViewById(R.id.spinner2);
		String[] items2 = new String[] { "USD", "VND", "EUR" };
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
			from = EUR;
		} else {
			from = VND;
		}

		double to = 0;
		if (ToCurrency.compareTo("USD") == 0) {
			to = USD;
		} else if (ToCurrency.compareTo("ERU") == 0) {
			to = EUR;
		} else {
			to = VND;
		}
		//Toast.makeText(getApplicationContext(), from + " " + to, Toast.LENGTH_SHORT).show();
		return (to/from);
	}

	private void convert(){
    	String from = sp1.getSelectedItem().toString();
    	String to = sp2.getSelectedItem().toString();
    	
    	Double exchange = calExchange(from, to);
    	
    	Double fromvalue = Double.parseDouble(edFrom.getText().toString());
    	fromvalue = fromvalue*exchange;
    	tvTo.setText(fromvalue.toString());
    	
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
