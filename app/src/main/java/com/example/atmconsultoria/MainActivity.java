package com.example.atmconsultoria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.atmconsultoria.databinding.ActivityMainBinding;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enviarEmail();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
            R.id.nav_principal, R.id.nav_clientes, R.id.nav_contato,
            R.id.nav_servico, R.id.nav_sobre
        )
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void enviarEmail() {

        String celular = "tel:11996352894";
        String imagem = "https://feriasperfeitas.com/wp-content/uploads/2022/04/caribe-1140x628.webp";
        String endereco = "https://www.google.com/maps/place/Central+Park/@40.781215,-74.0002896,14z/data=!3m1!4b1!4m5!3m4!1s0x89c2589a018531e3:0xb9df1f7387a94119!8m2!3d40.7812199!4d-73.9665138";

        //Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse(celular));
        //Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse(imagem));
        //Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse(endereco));

        Intent intent = new Intent( Intent.ACTION_SEND);

        intent.putExtra( Intent.EXTRA_EMAIL, new String[]{"atendimento@atmconsultoria.com.br"});
        intent.putExtra( Intent.EXTRA_SUBJECT, "Contato pelo App" );
        intent.putExtra( Intent.EXTRA_TEXT, "Mensagem automática" );


        //intent.setType("message/rfc822");
        //intent.setType("text/plain");
        //intent.setType("image/*");
        intent.setType("application/pdf");


        startActivity( Intent.createChooser( intent, "Escolha um App de e-mail" ));

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}