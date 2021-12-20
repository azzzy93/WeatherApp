package kg.geektech.weatherapp.ui.fragments.city_select_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.databinding.FragmentCitySelectBinding;

public class CitySelectFragment extends Fragment {
    private FragmentCitySelectBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCitySelectBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListeners();
    }

    private void initListeners() {
        binding.btnSendCityName.setOnClickListener(v -> {
            String cityName = binding.etSetCity.getText().toString();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
            navController.navigate(CitySelectFragmentDirections.actionCitySelectFragmentToWeatherFragment(cityName));
        });
    }
}