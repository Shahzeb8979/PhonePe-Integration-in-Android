package com.neo.phonepeintegration

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phonepe.intent.sdk.api.UPIApplicationInfo
import com.phonepe.intent.sdk.api.PhonePe

class MainActivity : AppCompatActivity(){

    private lateinit var phonePeSDKHelper: PhonePeSDKHelper
    private lateinit var upiAppsRecyclerView: RecyclerView
    private lateinit var upiAppsAdapter: UpiAppsAdapter
    private lateinit var proceedButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phonePeSDKHelper = PhonePeSDKHelper(this)

        val button = findViewById<Button>(R.id.button)
        upiAppsRecyclerView = findViewById(R.id.upi_apps_recycler_view)
        upiAppsRecyclerView.layoutManager = LinearLayoutManager(this)
        proceedButton = findViewById(R.id.button_proceed)
        button.setOnClickListener {
            val upiApps = phonePeSDKHelper.fetchInstalledUpiApps()
            upiAppsAdapter = UpiAppsAdapter(upiApps)
            upiAppsRecyclerView.adapter = upiAppsAdapter
            proceedButton.visibility = Button.VISIBLE
        }
        proceedButton.setOnClickListener {
            // Handle proceed button click
            println("Proceed button clicked!")
        }
    }
}

class PhonePeSDKHelper(private val context: Context) {
    init {
        PhonePe.init(context)
    }

    private val packageManager: PackageManager = context.packageManager
    fun fetchInstalledUpiApps(): List<ExtendedUPIApplicationInfo> {
        return PhonePe.getUpiApps().map { appInfo ->
            val appName = appInfo.applicationName
            val packageName = appInfo.packageName
            val appIcon = packageManager.getApplicationIcon(packageName);
            ExtendedUPIApplicationInfo(appName, packageName, appIcon)
        }
    }
}
data class ExtendedUPIApplicationInfo(
    val applicationName: String,
    val packageName: String,
    val appIcon: Drawable?
)
class UpiAppsAdapter(private val upiApps: List<ExtendedUPIApplicationInfo>) :
    RecyclerView.Adapter<UpiAppsAdapter.UpiAppViewHolder>() {

    class UpiAppViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val appLabel = itemView.findViewById<TextView>(R.id.app_label)
        val appIcon: ImageView = itemView.findViewById(R.id.app_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpiAppViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.upi_app_item, parent, false)
        return UpiAppViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UpiAppViewHolder, position: Int) {
        val upiApp = upiApps[position]
        holder.appLabel.text = upiApp.applicationName;
        holder.appIcon.setImageDrawable(upiApp.appIcon)
    }

    override fun getItemCount(): Int {
        return upiApps.size
    }
}