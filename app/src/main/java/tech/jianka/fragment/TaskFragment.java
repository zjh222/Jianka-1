package tech.jianka.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import tech.jianka.activity.R;
import tech.jianka.adapter.ItemAdapter;
import tech.jianka.data.TaskData;

/**
 * Created by Richa on 2017/8/6.
 */

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskFragment extends Fragment implements ItemAdapter.ItemClickListener {
    public static final String ARG_FRAGMENT_TYPE = "TYPE";

    private static Toast mToast;

    private int fragmentType;

    private OnFragmentInteractionListener mListener;
    private View view;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    public ItemAdapter adapter;

    public TaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param fragmentType
     * @return A new instance of fragment TabsFragment.
     */
    public static TaskFragment newInstance(int fragmentType) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_FRAGMENT_TYPE, fragmentType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentType = getArguments().getInt(ARG_FRAGMENT_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_task, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.task_group_recycler_view);
        layoutManager = new GridLayoutManager(getActivity(), 2, GridLayout.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        TaskData data = new TaskData();
        adapter = new ItemAdapter(data.getTaskGroup(), ItemAdapter.TASK_GROUP, this);
        recyclerView.setAdapter(adapter);
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

//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(int clickedCardIndex) {
        // TODO: 2017/7/26 处理卡片单击事件

    }

    @Override
    public void onItemLongClick(final int clickedCardIndex) {
        builder = new AlertDialog.Builder(getContext());
        String[] options = getActivity().getResources().getStringArray(R.array.task_group_options);
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




