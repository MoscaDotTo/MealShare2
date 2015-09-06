package pennapps2015.mealshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Submit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        setTitle("New Post");
        final globals globalVars = globals.getInstance();

        Button submit = (Button) findViewById(R.id.btnSend);
        submit.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
              Spinner cat = (Spinner)findViewById(R.id.spinner);
              String catText = cat.getSelectedItem().toString();
              EditText whatTextHolder = (EditText)findViewById(R.id.inSharing);
              String whatText = whatTextHolder.getText().toString();
              EditText whereTextHolder = (EditText)findViewById(R.id.inWhere);
              String whereText = whereTextHolder.getText().toString();
              ParseObject entry = new ParseObject("entry");
              entry.put("username", globalVars.getUSN());
              entry.put("category",catText);
              entry.put("postContent",whatText.toString());
              entry.put("givenLocation",whereText.toString());
              entry.put("pinPoint", globalVars.getCurrentLocation());
              entry.saveInBackground();
              finish();
              MainActivity tmpMain = new MainActivity();


          }
            
        });
    }
}
