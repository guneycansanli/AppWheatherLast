package com.example.myapplication.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.myapplication.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var fusedLocationClient: FusedLocationProviderClient? = null    // for last location API
    private var lastLocation: Location? = null
    private var latitudeLabel: String? = null
    private var longitudeLabel: String? = null
    private var latitudeText: TextView? = null
    private var longitudeText: TextView? = null
    private var btnClosePlace: Button? = null
    private var deneme: String? = null // todo: YORUM: tüm tanımlar anlamlı isimlendirilmeli.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // todo: YORUM: onCreate içerisi olabildiğince sade ve kodlar metotlara ayrılmalıki okunabilirlik artsın.
        //  Metotlara böldüm, Yorumladığım alanı uçurabilirsin :)
        /*
        latitudeLabel = resources.getString(R.string.latitudeBabel)    //string label
        longitudeLabel = resources.getString(R.string.longitudeBabel)
        latitudeText = findViewById<View>(R.id.latitudeText) as TextView    //find view by id
        longitudeText =
            findViewById<View>(R.id.longitudeText) as TextView  //we found from XML ((xml den bulduk  (Main de))
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        var btnClosePlace = findViewById<Button>(R.id.btnClosePlace)  //take btnClosePlace
        btnClosePlace.setOnClickListener(this)

        val url = "https://www.metaweather.com//api/location/search/?lattlong=(latt),(long)"
         */

        setLabels()
        initViews()
        setListeners()
    }

    private fun setLabels() {
        latitudeLabel = resources.getString(R.string.latitudeBabel)    //string label
        longitudeLabel = resources.getString(R.string.longitudeBabel)
    }

    private fun initViews() {
        latitudeText = findViewById<TextView>(R.id.latitudeText)
        longitudeText = findViewById<TextView>(R.id.longitudeText)
        btnClosePlace = findViewById<Button>(R.id.btnClosePlace)  //take btnClosePlace
    }

    private fun setListeners() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        btnClosePlace?.setOnClickListener(this)
    }

    public override fun onStart() {                                                                   // (1)
        super.onStart()                      //app start override
        if (!checkPermissions()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {       // sdk control
                requestPermissions()
            }
        } else {
            getLastLocation()
        }
    }

    private fun getLastLocation() {                                     //last location method
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient?.lastLocation!!.addOnCompleteListener(this) { task ->
            if (task.isSuccessful && task.result != null) {
                lastLocation = task.result
                latitudeText!!.text = latitudeLabel + ": " + (lastLocation)!!.latitude
                longitudeText!!.text = longitudeLabel + ": " + (lastLocation)!!.longitude
                var deneme = (lastLocation)!!.latitude
                Log.i(TAG, "denemmed" + deneme)

            } else {
                Log.w(TAG, "getLastLocation: exception", task.exception)
                showMessage("No location detected. Make sure location is enabled on the device that means we used last location.")
            }
        }
    }

    private fun showMessage(string: String) {
        val container = findViewById<View>(R.id.linearLayout)
        if (container != null) {
            Toast.makeText(this@MainActivity, string, Toast.LENGTH_LONG).show()
        }
    }

    private fun showSnackbar(
        mainTextStringId: String, actionStringId: String,
        listener: View.OnClickListener
    ) {
        Toast.makeText(this@MainActivity, mainTextStringId, Toast.LENGTH_LONG).show()
    }

    private fun checkPermissions(): Boolean {                                                 //Permission control Bolean true false format that means return false or true ...
        val permissionState =
            ActivityCompat.checkSelfPermission(                           // val ca nt changed
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION                                    //Android manifest
            )
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            REQUEST_PERMISSIONS_REQUEST_CODE
        )
    }

    private fun requestPermissions() {                           // request for using locatioon  (2)
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION           //
        )
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.")
            showSnackbar("Location permission is needed for core functionality", "Okay",
                View.OnClickListener {
                    startLocationPermissionRequest()        //take
                })
        } else {
            Log.i(TAG, "Requesting permission")
            startLocationPermissionRequest()          // with up side toke permission
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        Log.i(TAG, "onRequestPermissionResult")
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> {
                    // If user interaction was interrupted, the permission request is cancelled and you
                    // receive empty arrays.
                    Log.i(TAG, "User interaction was cancelled.")
                }
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
                    // Permission granted.
                    getLastLocation()
                }
                else -> {
                    showSnackbar("Permission was denied", "Settings",
                        View.OnClickListener {
                            // Build intent that displays the App settings screen.
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts(
                                "package",
                                Build.DISPLAY, null
                            )
                            intent.data = uri
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }

    companion object {
        private val TAG = "LocationProvider"
        private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    }

    override fun onClick(v: View?) {
        val intent = Intent(applicationContext, NearCitiesActivity::class.java)
        intent.putExtra("latitude", lastLocation?.latitude)
        intent.putExtra("longitude", lastLocation?.longitude)
        // System.out.println((lastLocation)!!.latitude)
        // System.out.println((lastLocation)!!.longitude)

        Log.d("Latitude : ", lastLocation?.latitude.toString())
        Log.d("Longitude :", lastLocation?.longitude.toString())
        startActivity(intent)
    }
}