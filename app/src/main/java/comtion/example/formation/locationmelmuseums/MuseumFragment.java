package comtion.example.formation.locationmelmuseums;


import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import comtion.example.formation.locationmelmuseums.model.Museum;


/**
 * A simple {@link Fragment} subclass.
 */
public class MuseumFragment extends Fragment implements AdapterView.OnItemClickListener {

    private List<Museum> museumList;
    private ListView museumListView;


    public MuseumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDataFromHttp();

        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_museum, container, false);

       museumListView = view.findViewById(R.id.museumListView);

       museumListView.setOnItemClickListener(this);

       return view;
    }

    private void processResponse(String response){

        //Transformation de la réponse json en list de Museum
        museumList = responseToList(response);

        //Conversion de la liste de Museum en un tableau de String comportant
        //uniquement le nom des musées
        String[] data = new String[museumList.size()];
        for (int i=0; i < museumList.size(); i++){
            data[i] = museumList.get(i).getName();
        }

        //Définition d'un ArrayAdapter pour alimenter la ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this.getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                data
        );

        museumListView.setAdapter(adapter);
    }

    private void getDataFromHttp(){
        String url = "https://opendata.lillemetropole.fr/api/records/1.0/search/?dataset=musee&facet=commune&facet=code_postal";


        //Définition de la requête
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                //Gestionnaire de succès
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("HTTP", response);
                        processResponse(response);
                    }
                },
                //Gestionnaire d'erreur
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.i("HTTP",error.getMessage());
                    }
                }
        );

        //Ajouter la requête à la file d'exécution
        Volley.newRequestQueue(this.getActivity())
                .add(request);
    }

    //Conversion d'une réponse JSON(chaine de caractère)
    //en une liste Museum
    private List<Museum> responseToList(String response) {

        List<Museum> list = new ArrayList<>();

        try{
            JSONObject jsonObject = new JSONObject(response);

            JSONArray array = new JSONArray(jsonObject.getString("records"));

            JSONObject item;
            for(int i = 0; i < array.length(); i++){

                //Transforme tableau en objet
                JSONObject obj = new JSONObject(array.getString(i));

                //Création d'un nouveau musée
                Museum museum = new Museum();

                //Hydratation du musée
                item = (JSONObject) array.get(i);
                JSONObject field = item.getJSONObject("fields");


                if(field.has("libelle")){
                    museum.setName((String) field.get("libelle"));
                } else{
                    museum.setName("inconnu");
                }

                if(field.has("adresse")){
                    museum.setName((String) field.get("adresse"));
                } else{
                    museum.setName("inconnu");
                }

                if(field.has("url")){
                    museum.setName((String) field.get("url"));
                } else{
                    museum.setName("inconnu");
                }

                JSONArray geo = item.getJSONObject("geometry").getJSONArray("coordinates");
                museum.setLatitude(geo.getDouble(0));
                museum.setLongitude(geo.getDouble(1));

                //Ajout du musée à la liste
                list.add(museum);
                }
        } catch (JSONException e){
            e.printStackTrace();
        }

        return list;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

    }
}
