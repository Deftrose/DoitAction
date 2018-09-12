package com.example.orquoll.swen90014_2018_or_quoll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.Adapters.MyStrengthAdapter;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Strength;

import org.w3c.dom.Text;

public class MyStrengthActivity extends AppCompatActivity {

    private TextView btn_back;
    private RecyclerView rv_my_strength;
    private TextView strengthTitle;
    private ImageView strengthImage;
    private TextView currentLevel;
    private Strength thisStrength;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_strength);

        rv_my_strength = (RecyclerView) findViewById(R.id.rv_strength_action);
        btn_back = (TextView) findViewById(R.id.btn_back_my_strength);
        strengthImage = (ImageView) findViewById(R.id.my_strength_img);
        strengthTitle = (TextView) findViewById(R.id.my_strength_title);
        currentLevel = (TextView) findViewById(R.id.my_strength_level);
        progressBar = (ProgressBar) findViewById(R.id.my_strength_progress);
        DAOFactory newDAOFactory = new DAOFactory();
        Bundle bundle = getIntent().getExtras();

        thisStrength = newDAOFactory.getStrengthDAOImp().getStrengthById(bundle.getLong("StrengthId"));

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setStrength();
        rv_my_strength.setLayoutManager(new LinearLayoutManager(this));
        rv_my_strength.setAdapter(new MyStrengthAdapter(this,bundle.getLong("StrengthId")));

    }

    private void setStrength(){
        int level = thisStrength.getPoints()/50;
        strengthTitle.setText(thisStrength.getStrength_Title());
        strengthImage.setImageResource(thisStrength.getDrawableId());
        currentLevel.setText("Current level is "+level);
        progressBar.setMax(100);
        progressBar.setProgress(thisStrength.getPoints()-level*50);

    }
}