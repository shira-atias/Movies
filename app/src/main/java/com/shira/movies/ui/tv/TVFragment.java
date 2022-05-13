package com.shira.movies.ui.tv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shira.movies.R;
import com.shira.movies.adapter.CategoryTVAdapter;
import com.shira.movies.databinding.FragmentTvBinding;
import com.shira.movies.models.Tv;

import java.util.List;


public class TVFragment extends Fragment {

    private TVViewModel tvViewModel;
    private FragmentTvBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tvViewModel =
                new ViewModelProvider(this).get(TVViewModel.class);

        binding = FragmentTvBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.tvTVDrama.setOnClickListener(v -> GoToGenreFragment("18",v));
        binding.tvTVComedy.setOnClickListener(v -> GoToGenreFragment("35",v));
        binding.tvTVReality.setOnClickListener(v -> GoToGenreFragment("10764",v));
        binding.tvTVAnimation.setOnClickListener(v -> GoToGenreFragment("16",v));
        binding.tvTVSoap.setOnClickListener(v -> GoToGenreFragment("10766",v));
        binding.tvTVFamily.setOnClickListener(v -> GoToGenreFragment("10751",v));
        binding.tvTVDocumentary.setOnClickListener(v -> GoToGenreFragment("99", v));

        tvViewModel.getTvRealityMutableLiveData().observe(getViewLifecycleOwner(),tvs -> {
            getRecyclerView(root,R.id.rvTVReality,tvs);
        });
        tvViewModel.getTvComedyMutableLiveData().observe(getViewLifecycleOwner(),movies -> {
            getRecyclerView(root, R.id.rvTVComedy,movies);
        });
        tvViewModel.getTvDramaMutableLiveData().observe(getViewLifecycleOwner(),movies -> {
            getRecyclerView(root, R.id.rvTVDrama,movies);
        });
        tvViewModel.getTvAnimationMutableLiveData().observe(getViewLifecycleOwner(),movies -> {
            getRecyclerView(root,R.id.rvTVAnimation,movies);
        });
        tvViewModel.getTvSoapMutableLiveData().observe(getViewLifecycleOwner(),movies -> {
            getRecyclerView(root,R.id.rvTVSoap,movies);
        });
        tvViewModel.getTvFamilyMutableLiveData().observe(getViewLifecycleOwner(),movies -> {
            getRecyclerView(root,R.id.rvTVFamily,movies);
        });
        tvViewModel.getTvDocumentaryMutableLiveData().observe(getViewLifecycleOwner(),movies -> {
            getRecyclerView(root,R.id.rvTVDocumentary,movies);
        });

        return root;
    }
    private void getRecyclerView(View root, int idRes, List<Tv> tvs){
        RecyclerView rvMovies = root.findViewById(idRes);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rvMovies.setAdapter(new CategoryTVAdapter(tvs));
    }
    private void GoToGenreFragment(String idGenre , View v){
        Bundle arg = new Bundle();
        arg.putString("id",idGenre);
        arg.putString("fragment","Tv");
        Navigation.findNavController(v).navigate(R.id.action_navigation_tv_to_genreFragment,arg);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}