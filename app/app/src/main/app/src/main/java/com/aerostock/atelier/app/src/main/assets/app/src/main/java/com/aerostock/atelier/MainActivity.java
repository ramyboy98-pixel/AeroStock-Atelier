package com.aerostock.atelier;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);

WebView web=new WebView(this);

String html=
"<html><body style='background:#87CEEB;font-family:Arial;padding:20px;'>"

+"<div style='background:silver;padding:20px;border-radius:20px;text-align:center;'>"
+"<h1>✈️🔧 AeroStock Atelier</h1>"
+"<p>Gestion outillage aviation militaire</p>"
+"</div>"

+"<div style='background:white;margin-top:20px;padding:15px;border-radius:15px;'>"
+"<h2>Recherche</h2>"
+"<input style='width:100%;padding:12px;' placeholder='Rechercher outil'>"
+"</div>"

+"<div style='background:white;margin-top:20px;padding:15px;border-radius:15px;'>"
+"<h2>Sortie Outil</h2>"
+"Nom Ouvrier<br><br>"
+"Nom Outil<br><br>"
+"Numéro Série<br><br>"
+"<button>Enregistrer</button>"
+"</div>"

+"<div style='background:white;margin-top:20px;padding:15px;border-radius:15px;'>"
+"<h2>Inventaire</h2>"
+"Clé 12 — Disponible<br>"
+"Tournevis — Sortie<br>"
+"</div>"

+"</body></html>";

web.loadDataWithBaseURL(null,html,"text/html","utf-8",null);

setContentView(web);

}
}
