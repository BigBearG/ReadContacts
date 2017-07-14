package text.readcontacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<contact>contactList=new ArrayList<>();
    private   ListView contactsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsView = (ListView)findViewById(R.id.lv);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }
    }
    public void readContact(View view){
        Cursor cusor=null;
        try{
            cusor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if (cusor!=null){
                while (cusor.moveToNext()){
                    String displayname=cusor.getString(cusor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String displayphone=cusor.getString(cusor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contact a=new contact();
                    a.setName(displayname);
                    a.setPhone(displayphone);
                    contactList.add(a);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cusor!=null){
                cusor.close();
                contactsView.setAdapter(new MyAdapter(contactList,this));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:if (grantResults.length<0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"没有添加权限",Toast.LENGTH_LONG).show();
            }
        }
    }

}
