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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.databinding.FragmentCitySelectBinding;

public class CitySelectFragment extends Fragment implements OnMapReadyCallback {
    private FragmentCitySelectBinding binding;
    private GoogleMap gMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCitySelectBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;

        gMap.setOnMapClickListener(latLng -> {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            gMap.clear();
            gMap.addMarker(markerOptions);
            gMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                    CameraPosition.builder()
                            .zoom(10f)
                            .target(latLng)
                            .bearing(100f)
                            .tilt(30f)
                            .build()
            ));

            gMap.setOnMarkerClickListener(marker -> {
                double lat = latLng.latitude;
                double lon = latLng.longitude;
                Bundle bundle = new Bundle();
                bundle.putDouble("keyLat", lat);
                bundle.putDouble("keyLon", lon);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
                navController.navigate(R.id.weatherFragment, bundle);
                return false;
            });
        });
    }
}