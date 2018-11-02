package br.ufms.facom.mariocarvalho.bookkeeping.util;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.channels.NoConnectionPendingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;

import br.ufms.facom.mariocarvalho.bookkeeping.R;

/**
 * Esta é a classe mais util de todas as classes do pacote util rsrsrsr.
 */
public class Util {
    private final static boolean DEBUG = true;
    private final static String TAG = "MY_TAG";
    private final static String TEXTO_CONTINUAR_COM = "Continuar com..";
    /**
     * Converte um objeto do tipo Bitmap para byte[] (array de bytes)
     *
     * @param imagem
     * @param qualidadeDaImagem
     * @return
     */
    public static byte[] converteBitmapPraByteArray(Bitmap imagem,
                                                    int qualidadeDaImagem) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagem.compress(CompressFormat.JPEG, qualidadeDaImagem, stream);
        return stream.toByteArray();
    }

    /**
     * Converte um objeto do tipo byte[] (array de bytes) para Bitmap
     *
     * @param imagem
     * @return
     */
    public static Bitmap converteByteArrayPraBitmap(byte[] imagem) {
        return BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
    }

    /**
     * Mostra uma Mensagem dentro de uma caixa de dialogo e fica
     * congelada na tela até que se prescione OK, ou o botão onBackPressed().
     * É melhor que o Toast porque contem um tento maior para ser lido
     *
     * @param sms : String contendo o texto que se deseja mostrar
     * @param context : Contexto da Aplicação
     * mostra o AlertDialog.Builder
     */

    public static void mostrarAlertaBuilder(String sms,Context context) {

        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setMessage(""+sms);
        alerta.setNeutralButton("OK",null);
        alerta.create().show();
    }

    public static void mostrarAlertaBuilder(String title, String sms,Context context) {

        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setTitle(title);
        alerta.setMessage(""+sms);
        alerta.setNeutralButton("OK",null);
        alerta.create().show();
    }
    /**
     * Mostra uma Mensagem em um Toast e o congelamento depende da duração passada: LENGTH_LONG ou LENGTH_SHORT
     * É um metódo util para poder facilitar e acelerar a criação de Toasts.
     *
     * @param sms : String contendo o texto que se deseja mostrar
     * @param context : Contexto da Aplicação
     * @param  duracao : Tempo de Duração do Toast: LENGTH_LONG ou LENGTH_SHORT
     * mostra o Toast
     */

    public static void mostrarToast(String sms,Context context,int duracao) {

        Toast toast = Toast.makeText(context, sms, duracao);
        toast.show();
    }

    /**
     *
     * @param context: Context para Intent
     * @param numero: Que se realizara a ligacao
     */

    public static void ligar(Context context, String numero)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+numero));

        try {
            Intent chooser = Intent.createChooser(intent, TEXTO_CONTINUAR_COM);
            context.startActivity(chooser);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(context, "Error: Não foi possível realizar chamada!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     * @param context
     * @param numero
     * @param texto
     */
    public static void enviarSMS(Context context, String numero, String texto)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("sms:"+numero));
        intent.putExtra("sms_body", texto);

        try {
            Intent chooser = Intent.createChooser(intent, TEXTO_CONTINUAR_COM);
            context.startActivity(chooser);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(context, "Error: Não foi possível enviar SMS!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     * @param context
     * @param texto
     */
    public static void compartilhar(Context context, String texto){

        try{
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            final StringBuilder builder = new StringBuilder();
            builder.append(texto);
            builder.append("\n");
            i.putExtra(Intent.EXTRA_TEXT, builder.toString());
            context.startActivity(Intent.createChooser(i, TEXTO_CONTINUAR_COM));
        }catch (Exception e){
            AlertDialog.Builder enviarEmail = new AlertDialog.Builder(context);
            enviarEmail.setMessage("Seu dispositivo não suporta essa função!");
            enviarEmail.create().show();
        }
    }

    /**
     *
     * @param context: Context da Intent
     * @param url: URL para abrir
     */
    public static void abrirURL(Context context, String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        try {
            Intent chooser = Intent.createChooser(intent, TEXTO_CONTINUAR_COM);
            context.startActivity(chooser);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(context, "Error: Não foi possível abrir a URL", Toast.LENGTH_SHORT).show();
        }
    }

    public static void enviarEmail(Context context, String endereco_receptor, String subject, CharSequence texto_conteudo)
    {
        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType("message/rfc822");
        mailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{endereco_receptor});
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mailIntent.putExtra(Intent.EXTRA_TEXT   , texto_conteudo);

        try {
            Intent chooser = Intent.createChooser(mailIntent, TEXTO_CONTINUAR_COM);
            context.startActivity(chooser);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(context, "Error: Não foi possível enviar o E-mail!", Toast.LENGTH_SHORT).show();
        }
    }

    public static void startIntent(Context context, AppCompatActivity activity)
    {
        Intent newIntent = new Intent(context.getApplicationContext(), activity.getClass());
        context.startActivity(newIntent);

    }
    public static void startIntentParseOneValue(Context context, AppCompatActivity activity, String key, int value)
    {
        Intent newIntent = new Intent(context, activity.getClass());
        newIntent.putExtra(key,value);
        context.startActivity(newIntent);
    }

    /** LatLng sydney = new LatLng(-33.867, 151.206);*/
    /*
    public static LatLng getLocationInfo(String endereco, final Context mContext) {
        final LatLng[] mLocalizacaoCoordenadas = {null};
        if (TextUtils.isEmpty(endereco)) {
            return null;
        }
        if (DEBUG) {
            Log.d(TAG, "endereco : " + endereco);
        }

        final String URL_LACALIZATION =  "http://maps.google."
                + "com/maps/api/geocode/json?endereco=" + endereco
                + "ka&sensor=false";

        StringRequest mStringRequestContato = new StringRequest(
                Request.Method.POST,
                URL_LACALIZATION,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try{

                                JSONObject jsonObject = new JSONObject(response.toString());
                                JSONObject location = jsonObject.getJSONArray("results")
                                        .getJSONObject(0).getJSONObject("geometry")
                                        .getJSONObject("location");
                                double longitude = location.getDouble("lng");
                                double latitude = location.getDouble("lat");
                                if (DEBUG) {
                                    Log.d(TAG, "location : (" + longitude + "," + latitude + ")");
                                }

                                mLocalizacaoCoordenadas[0] = new LatLng(latitude, longitude);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                    }//Fim do onResponse
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Util.mostrarToast("Erro enquanto carregava os dados de "+ URL_LACALIZATION +"...", mContext , Toast.LENGTH_SHORT);
                        Log.d("TAG-ERROR: ",error.toString());
                        Log.e("TAG-URL: ","Erro enquanto carregava os dados de "+ URL_LACALIZATION +"...");
                        error.printStackTrace();
                    }
                }


        );//Fim mStringRequest
        // mStringRequest.setRetryPolicy(new DefaultRetryPolicy(2000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        SingletonVolley.getInstance((mContext).getApplicationContext()).addToRequestQueue(mStringRequestContato);

        return mLocalizacaoCoordenadas[0];
    }

*/

    /**
     * Função que busca o primeiro caractere das estrings
     * @param mTexto
     * @return
     */

    public static String buscarPrimeiroCaractere(String mTexto) {
        String resultado = String.valueOf(mTexto.charAt(0));

        return resultado.toUpperCase();
    }

    public static int buscarIndiceCaractere(String mTexto) {
        //return mTexto[0] - 'A';
        String indice = String.valueOf(mTexto.charAt(0));

        String mIndice = indice.toUpperCase();

        if(mIndice.equals("A")){
            return 0;
        }
        else if(mIndice.equals("B")){
            return 1;
        }
        else if(mIndice.equals("C")){
            return 2;
        }
        else if(mIndice.equals("D")){
            return 3;
        }
        else if(mIndice.equals("E")){
            return 4;
        }
        else if(mIndice.equals("F")){
            return 5;
        }else if(mIndice.equals("G")){
            return 6;
        }
        else if(mIndice.equals("H")){
            return 7;
        }
        else if(mIndice.equals("I")){
            return 8;
        }
        else if(mIndice.equals("J")){
            return 9;
        }
        else if(mIndice.equals("K")){
            return 10;
        }
        else if(mIndice.equals("L")){
            return 11;
        }
        else if(mIndice.equals("M")){
            return 12;
        }
        else if(mIndice.equals("N")){
            return 13;
        }
        else if(mIndice.equals("O")){
            return 14;
        }
        else if(mIndice.equals("P")){
            return 15;
        }else if(mIndice.equals("Q")){
            return 16;
        }
        else if(mIndice.equals("R")){
            return 17;
        }
        else if(mIndice.equals("S")){
            return 18;
        }
        else if(mIndice.equals("T")){
            return 19;
        }
        else if(mIndice.equals("U")){
            return 20;
        }
        else if(mIndice.equals("V")){
            return 21;
        }
        else if(mIndice.equals("X")){
            return 22;
        }
        else if(mIndice.equals("Y")){
            return 23;
        }
        else if(mIndice.equals("W")){
            return 24;
        }
        else if(mIndice.equals("Z")){
            return 25;
        }else{
            return 5;
        }
    }

    public static boolean checkConnection(Context context) throws NoConnectionPendingException {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) {
            return false;
        } else {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String removeAccent(String text){
        String result = Normalizer.normalize(text, Normalizer.Form.NFD);
        return result.replaceAll("[^\\p{ASCII}]", "");
    }

    public static String replacePhone(String phone) {
        String result = phone.replace("(", "");
        result = result.replace(")", "");
        result = result.replace(".", "");
        result = result.replace("-", "");
        return result;
    }


    /**
     * Máscara textos
     * Exemplo de Utilizacao
     txtCEP = (EditText)findViewById(R.id.txt_cep);
     TextWatcher cepMask = Util.Mask.insert("######-###", txtCEP);
     txtCEP.addTextChangedListener(cepMask);
     *
     * */

    public abstract static class Mask {

        /**
         * @Description: Tira a mascara
         * @param text
         * @return
         */
        public static String unmask(String text) {
            return text.replaceAll("[.]", "").replaceAll("[-]", "")
                    .replaceAll("[/]", "").replaceAll("[(]", "")
                    .replaceAll("[)]", "");
        }

        /**
         * Inseri a máscára em tempo real no EditText
         * @param mask
         * @param ediTxt
         * @return
         */
        public static TextWatcher insert(final String mask, final EditText ediTxt) {

            return new TextWatcher() {
                boolean isUpdating;
                String old = "";

                public void onTextChanged(CharSequence textoParaMascarar, int start,
                                          int before, int count) {
                    String texto = Mask.unmask(textoParaMascarar.toString());
                    String mascara = "";

                    if (isUpdating) {
                        old = texto;
                        isUpdating = false;
                        return;
                    }

                    int i = 0;
                    for (char m : mask.toCharArray()) {
                        if (m != '#' && texto.length() > old.length()) {
                            mascara += m;
                            continue;
                        }
                        try {
                            mascara += texto.charAt(i);
                        } catch (Exception e) {
                            break;
                        }
                        i++;
                    }

                    isUpdating = true;
                    ediTxt.setText(mascara);
                    ediTxt.setSelection(mascara.length());
                }

                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void afterTextChanged(Editable s) {
                }
            };
        }
    }

    public static CharSequence getSuperscriptString(CharSequence base) {
        return getSuperscriptString(base, 0, base.length() - 1);
    }

    public static CharSequence getSuperscriptString(CharSequence base, int startFromIdx, int endAtIdx) {
        SpannableString str = new SpannableString(base);
        str.setSpan(new SuperscriptSpan(), startFromIdx, endAtIdx, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return str;
    }

    public static CharSequence getSubscriptString(CharSequence base) {
        return getSubscriptString(base, 0, base.length() - 1);
    }

    public static CharSequence getSubscriptString(CharSequence base, int startFromIdx, int endAtIdx) {
        SpannableString str = new SpannableString(base);
        str.setSpan(new SubscriptSpan(), startFromIdx, endAtIdx, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return str;
    }

    public static SpannableStringBuilder getSub(String texto){
        SpannableStringBuilder textSub = new SpannableStringBuilder("<pre>"+texto+"<br/></pre>");
        textSub.setSpan(new SubscriptSpan(), 0, textSub.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        /**Deve ser setado no TextView da seguinte forma:
         * mTextView.setText(textSub_Sup, TextView.BufferType.SPANNABLE);
         * */

        return textSub;
    }



    @SuppressWarnings("deprecation")
    public static Spanned getTextHTML(String texto){

        Spanned string;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            string = Html.fromHtml(texto, Html.FROM_HTML_MODE_LEGACY);
        } else{
            string = Html.fromHtml(texto);
        }

        return string;
    }
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    /**
     * O metodo getNetworkInfo da classe ConnectivityManager esta Deprecated.
     */
    public static boolean verificarConexao(Context context) {
        /* ConnectivityManager
        *.TYPE_MOBILE 0
        *.TYPE_WIFI 1
        *.TYPE_WIMAX 6
        *.TYPE_ETHERNET 9
        */
        try {
            ConnectivityManager cm = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            int[] p = {0,1,6,9};
            for (int i : p) {
                if (cm.getNetworkInfo(i).isConnected()){
                    //Toast.makeText(context, "Sucesso ao Verificar\n STATUS da rede", Toast.LENGTH_LONG).show();
                    return true;//Conexão OK
                }
            }
        } catch (Exception e) {
            //Toast.makeText(context,"ERRO ao Verificar\n STATUS da rede",Toast.LENGTH_LONG).show();
            //e.printStackTrace();
        }
        return false;//Sem Conexão
    }

    public static int getCor(int cor) {
        return R.color.colorPrimary;
    }

    public String getMD5(String input) {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
