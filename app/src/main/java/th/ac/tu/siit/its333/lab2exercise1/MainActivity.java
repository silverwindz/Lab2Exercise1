package th.ac.tu.siit.its333.lab2exercise1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    // expr = the current string to be calculated
    StringBuffer expr;
    int answer;
    int memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expr = new StringBuffer();
        updateExprDisplay();
    }

    public void updateExprDisplay() {
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(expr.toString());
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(Integer.toString(answer));
    }

    public void recalculate() {
        //Calculate the expression and display the output

        //Split expr into numbers and operators
        //e.g. 123+45/3 --> ["123", "+", "45", "/", "3"]
        //reference: http://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
        String e = expr.toString();
        String[] tokens = e.split("((?<=\\+)|(?=\\+))|((?<=\\-)|(?=\\-))|((?<=\\*)|(?=\\*))|((?<=/)|(?=/))");
        int ans =0;
        String op = "+";
        for(int i=0; i < tokens.length ;i++) {
            if (i % 2 == 0) {
                int x = Integer.parseInt(tokens[i]);
                if (op.equals("+")) {
                    ans = ans + x;
                } else if (op.equals("-")) {
                    ans = ans - x;
                } else if (op.equals("*")) {
                    ans = ans * x;
                } else if (op.equals("/")) {
                    ans = ans / x;
                }
            } else {
                op = tokens[i];
            }
        }

        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(Integer.toString(ans));
        answer=ans;
    }

    public void digitClicked(View v) {
        //d = the label of the digit button
        String d = ((TextView)v).getText().toString();
        //append the clicked digit to expr
        expr.append(d);
        //update tvExpr
        updateExprDisplay();
        //calculate the result if possible and update tvAns
        recalculate();
    }

    public void operatorClicked(View v) {
        //IF the last character in expr is not an operator and expr is not "",
        //THEN append the clicked operator and updateExprDisplay,
        //ELSE do nothing
        char o = expr.charAt(expr.length()-1);
        String d = ((Button)v).getText().toString();
        if (o!='+' && o!='-' && o!='*' && o!='/' && o!=' '){
            expr.append(d);
            updateExprDisplay();
        } else {

        }
        recalculate();
    }

    public void equalClicked(View v){
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(Integer.toString(answer));

    }

    public void ACClicked(View v) {
        //Clear expr and updateExprDisplay
        expr = new StringBuffer();
        answer = 0;
        updateExprDisplay();
        //Display a toast that the value is cleared
        Toast t = Toast.makeText(this.getApplicationContext(),
                "All cleared", Toast.LENGTH_SHORT);
        t.show();


    }

    public void BSClicked(View v) {
        //Remove the last character from expr, and updateExprDisplay
        if (expr.length() > 0) {
            expr.deleteCharAt(expr.length()-1);
            recalculate();
            updateExprDisplay();
        }
    }

    public void Mplus(View v){
        memory = memory + answer;
        Toast t = Toast.makeText(this.getApplicationContext(),
                "Memory is " + memory, Toast.LENGTH_SHORT);
        t.show();
    }

    public void Mminus(View v){
        memory = memory - answer;
        Toast t = Toast.makeText(this.getApplicationContext(),
                "Memory is " + memory, Toast.LENGTH_SHORT);
        t.show();
    }

    public void Mr(View v){
        TextView tvExpr = (TextView)findViewById(R.id.tvExpr);
        tvExpr.setText(Integer.toString(memory));
        TextView tvAns = (TextView)findViewById(R.id.tvAns);
        tvAns.setText(Integer.toString(memory));
        Toast t = Toast.makeText(this.getApplicationContext(),
                "Memory is " + memory, Toast.LENGTH_SHORT);
        t.show();
    }

    public void Mc(View v){
        memory = 0;
        Toast t = Toast.makeText(this.getApplicationContext(),
                "Memory is " + memory, Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
