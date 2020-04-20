package com.example.calculodeaposentadoria

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult;

class	MainActivity	:	Activity(){
    override	fun	onCreate(savedInstanceState:	Bundle?)	{
        super.onCreate(savedInstanceState)

        //definindo	o	arquivo	de	layout
        setContentView(R.layout.activity_main)
        //acessando	o	spinner
        val	spn_sexo	=	findViewById<Spinner>(R.id.spn_sexo)
        //acessando	a	caixa	de	idade
        val	txt_idade	=	findViewById<EditText>(R.id.txt_idade)
        //acessando	o	botão	de	calcular
        val	btn_calcular	=	findViewById<Button>(R.id.btn_calcular
        )
        //acessando	o	texto	de	resultado
        val	txt_resultado	=	findViewById<TextView>(R.id.txt_resultado)

        spn_sexo.adapter	=	ArrayAdapter<String>(this,	android.R.layout.simple_spinner_dropdown_item,
            listOf("masculino",	"feminino"))

        btn_calcular.setOnClickListener	{
            //aqui	vai	o	código	que	será	executado	quando	houver	um	click do botão

            btn_calcular.setOnClickListener	{
                //capturando	o	sexo	selecionado
                val	sexo	=	spn_sexo.selectedItem	as	String
                //capturando	a	idade	digitada
                val	idade	=	txt_idade.text.toString().toInt()
                //variável	para	guardar	o	resultado	do	cálculo

                var resultado = 0;
                //verificando	o	sexo	da	pessoa
                if(sexo	==	"masculino"){
                    resultado	=	65	-	idade
                }else{
                    resultado	=	60	-	idade
                }
                //Atualizando	a	tela	de	acordo	com	o	resultado	do	cálculo
                txt_resultado.text	=	"Faltam	$resultado	anos	para	você	se	aposentar."
            }

        }

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result!!.token

                // Log and toast
                val msg = getString(R.string.msg_token_fmt, token)
                Log.d(TAG, msg)
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            })


    }
}
