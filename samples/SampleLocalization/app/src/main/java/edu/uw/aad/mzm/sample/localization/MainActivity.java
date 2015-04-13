package edu.uw.aad.mzm.sample.localization;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Margaret on
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long costInDollars = 1000;

        // TODO Any exchange rate conversion...

        TextView upgradeTextView = (TextView) this.findViewById(R.id.upgradeTextView);

        // Get the default locale
        Locale locale = Locale.getDefault();
        // Format the number
        String costFormatted = NumberFormat.getCurrencyInstance(locale).format(costInDollars);

        // Format string
        String upgradeString = this.getResources().getString(R.string.upgrade);
        String costString = String.format("%s %s", upgradeString, costFormatted);

        // Update upgradeTextView
        upgradeTextView.setText(costString);
    }

}
