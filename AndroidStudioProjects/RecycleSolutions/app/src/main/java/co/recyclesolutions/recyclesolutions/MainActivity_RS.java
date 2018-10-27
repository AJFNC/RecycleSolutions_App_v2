package co.recyclesolutions.recyclesolutions;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import static co.recyclesolutions.recyclesolutions.MainActivity_RS.PlaceholderFragment.prop;


public class MainActivity_RS extends AppCompatActivity implements Proposal{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    //////

    public static String valor_sent;

    public String str_valor = "0,00";

    //private static Proposal prop;


    ////



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__rs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //prop.data_exchange(valor_sent);

                str_valor = valor_sent;

                sendEmail(str_valor);

                Snackbar.make(view, "Enviar e-mail para a RecycleSolutions", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public String data_exchange(String str_number) {


        str_valor = str_number;
        return str_valor;

    }

    /////////////////
    public void sendEmail(String ss) {


        String[] TO = {"contato@recyclesolutions.co"}; //E-mail address
        String[] CC = {"alexandre.cavalcanti@recyclesolutions.co;mykael.silva@recyclesolutions.co"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        // Change with subject, if desired
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Proposta");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Solicio coleta de resíduo de kkkkkk kg por R$" + ss + "\n" +
                "Endereço: yyyyyyyyyyyyyyy\n" +
                "Telefone (__) 9mcdu mcdu\n" +
                "\n" +
                "Grato,\n" +
                "Fulano de Tal");

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity_RS.this,
                    "Não tem cliente de email instalado!", Toast.LENGTH_SHORT).show();
        }
    }


    ////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity__r, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finishAffinity();
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        //private static final String ARG_VALOR = "1000,00";
        private static final String ARG_VALOR = "valor";

        /////////

        public static Proposal prop;



        ////////

       // public static Proposal prop;
        //public  Proposal prop;


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            //args.putString(ARG_VALOR, valor); //passando valor para o fragmento

            //valor_sent = args.getString(ARG_VALOR, valor);

            fragment.setArguments(args);

           // fragment.getArguments();
            return fragment;
        }

        /**
        public void onAttach(AppCompatActivity activity) {
            //super.onAttach(activity);
            prop = (Proposal)activity;
        }
        */


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_activity__r, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            EditText editText1 = (EditText) rootView.findViewById(R.id.editText1);
            valor_sent = editText1.getText().toString();


           // prop.data_exchange(valor_sent);

            return rootView;
        }



    }




    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            if (position + 1 == 1)

                return PlaceholderFragment.newInstance(position + 1);

            if (position + 1 == 2)

                return PlaceholderFragment.newInstance(position + 1);

            //if (position == 3)
            else
                return PlaceholderFragment.newInstance(position +1);


        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }


}
