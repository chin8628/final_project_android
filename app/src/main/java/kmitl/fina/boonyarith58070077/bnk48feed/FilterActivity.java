package kmitl.fina.boonyarith58070077.bnk48feed;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kmitl.fina.boonyarith58070077.bnk48feed.model.member.Member;

public class FilterActivity extends AppCompatActivity {

    private ListView listView;
    private SparseBooleanArray sparseBooleanArray;
    private List<String> listViewItems = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ButterKnife.bind(this);

        this.listView = findViewById(R.id.list_view);

        for (String member: Member.getMember()) {
            this.listViewItems.add(Character.toUpperCase(member.charAt(0)) + member.substring(1));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1, this.listViewItems
        );

        this.listView.setAdapter(adapter);
        sparseBooleanArray = listView.getCheckedItemPositions();

        Member.clearFilter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i=0; i<sparseBooleanArray.size(); i++) {
                    if (sparseBooleanArray.valueAt(i)) {
                        Member.addMemberFilter(listViewItems.get(sparseBooleanArray.keyAt(i)).toLowerCase());
                    }
                }
            }
        });
    }

    @OnClick(R.id.save)
    public void save() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
