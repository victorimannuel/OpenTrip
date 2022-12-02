package com.akademik.opentrip.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.akademik.opentrip.MainActivity
import com.akademik.opentrip.R
import com.akademik.opentrip.databinding.FragmentRegisterBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_auth.*
import org.checkerframework.checker.units.qual.A
import java.util.*
import kotlin.collections.HashMap

//class RegisterFragment : Google() {
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var fstore: FirebaseFirestore
    private lateinit var myRef: DocumentReference
    private lateinit var mCallbackManager: CallbackManager
    private lateinit var fAuth: FirebaseAuth
    private var firebaseUserID: String = ""
    private val TAG: String = LoginFragment::class.java.getSimpleName()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        mAuth = FirebaseAuth.getInstance()
//        fstore = FirebaseFirestore.getInstance()
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtMasuk.setOnClickListener {
            (context as AuthActivity).view_pager_auth.setCurrentItem(0, true)
        }
        binding.buttonDaftar.setOnClickListener {
            registerUser()
        }
        binding.btnFacebookRegister.setOnClickListener {
            facebookSignIn()
        }
//        binding.btnGoogleRegister.setOnClickListener {
//            signIn()
//        }
    }

    private fun facebookSignIn() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))
        LoginManager.getInstance().registerCallback(mCallbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                TODO("Not yet implemented")
            }

            override fun onError(error: FacebookException?) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        fAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("LoginFragment", "signInWithCredential:success")
                val user = fAuth.currentUser
                startActivity(Intent(activity, MainActivity::class.java))
//                    updateUI(user)
            } else {
                Log.w("LoginFragment", "signInWithCredential:failure", task.exception)
                Toast.makeText(this.context, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
//                    updateUI(null)
            }
        }
    }

    private fun registerUser() {
        val email = binding.txtDaftarEmail.text.toString()
        val name = binding.txtDaftarNama.text.toString()
        val noTelp = binding.txtDaftarNotelp.text.toString()
        val password = binding.txtDaftarPassword.text.toString()

        if (email == "") {
            Toast.makeText(this.context, "Please fill out all fields", Toast.LENGTH_LONG).show()
        }else if(name == "") {
            Toast.makeText(this.context, "Please fill out all fields", Toast.LENGTH_LONG).show()
        }else if(noTelp == "") {
            Toast.makeText(this.context, "Please fill out all fields", Toast.LENGTH_LONG).show()
        }else if(password == "") {
            Toast.makeText(this.context, "Please fill out all fields", Toast.LENGTH_LONG).show()
        }else {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        firebaseUserID = mAuth.currentUser!!.uid
                        myRef = fstore.collection("Users").document(firebaseUserID)

                        val userHashMap = HashMap<String, Any>()
                        userHashMap["uid"] = firebaseUserID
                        userHashMap["name"] = name
                        userHashMap["noTelp"] = noTelp
                        userHashMap["email"] = email
                        userHashMap["alamat"] = ""
                        userHashMap["jenisKelamin"] = ""

                        myRef.set(userHashMap).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("RegisterFragment", "SignUp:success")
                                val intent = Intent(this.context, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Log.w("RegisterFragment", "SignUp:failure", task.exception)
                                Toast.makeText(
                                    requireContext(), "SignUp failed.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Log.w("RegisterFragment", "SignUp:failure", task.exception)
                        Toast.makeText(
                            requireContext(), "SignUp failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}