package com.akademik.opentrip.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.akademik.opentrip.MainActivity
import com.akademik.opentrip.R
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
//import com.akademik.opentrip.Activity.MainActivity
//import com.akademik.opentrip.Activity.ResetPasswordActivity
import com.akademik.opentrip.databinding.FragmentLoginBinding
import com.akademik.opentrip.lib.Google
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import java.util.*

//class LoginFragment : Google() {
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var mCallbackManager: CallbackManager
    private lateinit var fAuth: FirebaseAuth
    private val TAG: String = LoginFragment::class.java.getSimpleName()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//
//        fAuth = FirebaseAuth.getInstance()
//        FacebookSdk.sdkInitialize(this.context)
//        mCallbackManager = CallbackManager.Factory.create()
//
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
        binding.txtBelumMendaftar.setOnClickListener {
            (context as AuthActivity).view_pager_auth.setCurrentItem(1, true)
        }
//        binding.buttonGoogleSignIn.setOnClickListener {
//            signIn()
//        }
////        binding.txtLupaPassword.setOnClickListener {
////            val intent = Intent(requireActivity(), ResetPasswordActivity::class.java)
////            startActivity(intent)
////        }
        binding.buttonManualSignIn.setOnClickListener {
            manualSignIn()
        }
//        binding.buttonFacebookSignIn.setOnClickListener {
//            facebookSignIn()
//        }
    }
//
//    private fun facebookSignIn() {
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))
//        LoginManager.getInstance().registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
//            override fun onSuccess(loginResult: LoginResult) {
//                Log.d(TAG, "facebook:onSuccess:$loginResult")
//                handleFacebookAccessToken(loginResult.accessToken)
//            }
//
//            override fun onCancel() {
//                TODO("Not yet implemented")
//            }
//
//            override fun onError(error: FacebookException?) {
//                TODO("Not yet implemented")
//            }
//        })
//    }
//
    private fun manualSignIn() {
//        val email = binding.txtEmail.text.toString()
//        val password = binding.txtPassword.text.toString()
//        if (email == "" && password == "") {
//            Toast.makeText(
//                requireContext(),
//                "Please fill Email and Password",
//                Toast.LENGTH_SHORT
//            )
//                .show()
//        } else if(password.length < 6) {
//            Toast.makeText(
//                requireContext(),
//                "Password at least 6 characters",
//                Toast.LENGTH_SHORT
//            )
//                .show()
//        }
//        else {
//            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(requireActivity()) { task ->
//                    if (task.isSuccessful) {
//                        Log.d("LoginFragment", "signInWithEmail:success")
//                        Toast.makeText(
//                            requireContext(), "Authentication succed.",
//                            Toast.LENGTH_LONG
//                        ).show()
                        val intent = Intent(this.context, MainActivity::class.java)
                        startActivity(intent)
//                    } else {
//                        Log.w("LoginFragment", "signInWithEmail:failure", task.exception)
//                        Toast.makeText(
//                            requireContext(), "Authentication failed.",
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }
//        }
    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        mCallbackManager.onActivityResult(requestCode, resultCode, data)
//    }
//
//    private fun handleFacebookAccessToken(token: AccessToken) {
//        Log.d(TAG, "handleFacebookAccessToken:$token")
//
//        val credential = FacebookAuthProvider.getCredential(token.token)
//        fAuth.signInWithCredential(credential).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                Log.d("LoginFragment", "signInWithCredential:success")
//                val user = fAuth.currentUser
//                startActivity(Intent(activity, MainActivity::class.java))
////                    updateUI(user)
//            } else {
//                Log.w("LoginFragment", "signInWithCredential:failure", task.exception)
//                Toast.makeText(this.context, "Authentication failed.",
//                    Toast.LENGTH_SHORT).show()
////                    updateUI(null)
//            }
//        }
//    }
}