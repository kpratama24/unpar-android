package id.ac.unpar.unparapps;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import id.ac.unpar.unparapps.Adapter.EventsAdapter;
import id.ac.unpar.unparapps.decorators.HighlightWeekendsDecorator;
import id.ac.unpar.unparapps.decorators.MySelectorDecorator;
import id.ac.unpar.unparapps.decorators.OneDayDecorator;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
//import butterknife.BindView;
//import butterknife.ButterKnife;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Events.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Events#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Events extends Fragment implements OnDateSelectedListener {
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
 // private Button week,month;

    MaterialCalendarView calendarView;
    RecyclerView recyclerView;
    TextView textView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Events() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Events.
     */
    // TODO: Rename and change types and number of parameters
    public static Events newInstance(String param1, String param2) {
        Events fragment = new Events();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.action_events, container, false);
        calendarView=v.findViewById(R.id.calendarView);
        textView=v.findViewById(R.id.dateContent);
          recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        calendarView.setOnDateChangedListener(this);
        calendarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);


        Calendar instance = Calendar.getInstance();
        calendarView.setSelectedDate(instance.getTime());

        Calendar instance1 = Calendar.getInstance();
        instance1.set(instance1.get(Calendar.YEAR), Calendar.JANUARY, 1);

        Calendar instance2 = Calendar.getInstance();
        instance2.set(instance2.get(Calendar.YEAR), Calendar.DECEMBER, 31);

        calendarView.state().edit()
                .setMinimumDate(instance1.getTime())
                .setMaximumDate(instance2.getTime())
                .commit();

        calendarView.addDecorators(
                new MySelectorDecorator(v.getContext()),
                new HighlightWeekendsDecorator(),
                oneDayDecorator
        );


        // calendarView.setOnMonthChangedListener(this);

        textView.setText(getSelectedDatesString());

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        oneDayDecorator.setDate(date.getDate());
        widget.invalidateDecorators();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext());
        horizontalLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);

        List<String> dataset = new LinkedList<String>();
        for (int i = 0; i < 100; i++){
            dataset.add("item" + i);
        }
        final EventsAdapter adapter = new EventsAdapter(dataset);

        recyclerView.setAdapter(adapter);


       textView.setText(getSelectedDatesString());
    }
//   // @OnClick(R.id.button_weeks)
//    public void onSetWeekMode() {
//        calendarView.state().edit()
//                .setCalendarDisplayMode(CalendarMode.WEEKS)
//                .commit();
//    }
//
//  //  @OnClick(R.id.button_months)
//    public void onSetMonthMode() {
//        calendarView.state().edit()
//                .setCalendarDisplayMode(CalendarMode.MONTHS)
//                .commit();
//    }
//    @Override
//    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
//        getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
//    }
    private String getSelectedDatesString() {
        CalendarDay date = calendarView.getSelectedDate();
        if (date == null) {
            return " ";
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext());
        horizontalLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        // anotherRecyclerView.setLayoutManager(horizontalLayoutManager);

        List<String> dataset = new LinkedList<String>();
        for (int i = 0; i < 100; i++){
            dataset.add("item" + i);
        }
        final EventsAdapter adapter = new EventsAdapter(dataset);

        recyclerView.setAdapter(adapter);
        return FORMATTER.format(date.getDate());
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
