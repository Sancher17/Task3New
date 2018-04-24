package com.example.alex.maps;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.alex.maps.dataMaps.MapsData;
import com.example.alex.task3new.R;
import com.example.alex.utils.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MapsAdapter extends RecyclerView.Adapter<MapsAdapter.ViewHolder> {

    private static Logger LOGGER = new Logger(MapsAdapter.class);

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.action)
    TextView action;

    @BindView(R.id.count)
    TextView count;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    public static ArrayList <MapsData> items = new ArrayList<>();

    private MapsData data = new MapsData();

    public MapsAdapter() {
        items.clear();
        createData();//create list of items
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    @Override
    public MapsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate
                (R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(MapsAdapter.ViewHolder holder, final int position) {

        CardView cardView = holder.cardView;
        ButterKnife.bind(this,cardView);

        name.setText(items.get(position).getName());
        action.setText(items.get(position).getAction());
        count.setText(String.valueOf((items.get(position).getResultOfCalculation())));

        if (items.get(position).getProgressBar()){
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }else {
            progressBar.setVisibility(ProgressBar.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void createData() {
//        LOGGER.log("createData");
        String action = "add";
        for (int i = 1; i < 4; i++) {
            items.add(new MapsData("TreeMap", action, data.getResultOfCalculation(), data.getProgressBar()));
            items.add(new MapsData("HashMap", action, data.getResultOfCalculation(), data.getProgressBar()));
            switch (i) {
                case 1:
                    action = "search";
                    break;
                case 2:
                    action = "remove";
                    break;
                default:
                    break;
            }
        }
    }
}
