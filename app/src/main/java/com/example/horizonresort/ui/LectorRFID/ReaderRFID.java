package com.example.horizonresort.ui.LectorRFID;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.horizonresort.databinding.FragmentHomeBinding;
import com.example.horizonresort.databinding.FragmentReaderrfidBinding;
import com.example.horizonresort.ui.home.HomeViewModel;

public class ReaderRFID extends Fragment     {
    // Inicializa el adaptador NFC en el constructor
    private NfcAdapter nfcAdapter;
    private FragmentReaderrfidBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReaderrfidBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicializar el adaptador NFC
        nfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());

        if (nfcAdapter == null) {
            // NFC no está soportado en este dispositivo
        } else {
            // Habilitar la detección de NFC
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    getActivity(), 0, new Intent(getActivity(), getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
            );
            IntentFilter[] intentFiltersArray = new IntentFilter[]{
                    new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED),
                    // Agrega más intent filters según sea necesario
            };
            String[][] techListArray = new String[][]{};

            nfcAdapter.enableForegroundDispatch(getActivity(), pendingIntent, intentFiltersArray, techListArray);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
