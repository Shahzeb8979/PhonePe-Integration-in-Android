[3:29 am, 26/6/2024] +91 72520 42256: # PhonePe SDK Integration

## Overview
This SDK integrates with the PhonePe SDK to fetch and display a list of installed UPI apps on the user's device.

## Setup

1. Add the SDK dependency to your project:
    groovy
    implementation 'com.example:phonesdkintegration:1.0.0'
    

2. Initialize the SDK in your application:
    kotlin
    val phonePeSDKHelper = PhonePeSDKHelper(context)
    val upiAppsFetcher = UPIAppsFetcher(phonePeSDKHelper)
    

3. Fetch and display UPI apps:
    kotlin
    val upiApps = upiAppsFetcher.getInstalledUPIApps()
    val bottomSheet = UPIAppsBottomSheet()
    bottomSheet.setUpiApps(upiApps)
    bottomSheet.show(supportFragmentManager, "UPIAppsBottomSheet")
    

## API Documentation

### PhonePeSDKHelper
- fetchInstalledUpiApps(): List<UpiApp>

### UPIAppsFetcher
- getInstalledUPIApps(): List<UpiApp>

### UPIAppsBottomSheet
- setUpiApps(upiApps: List<UpiApp>)
[10:46 pm, 26/6/2024] +91 72520 42256: https://meet.google.com/fkw-ztuu-otf
[10:46 pm, 26/6/2024] +91 72520 42256: aao
[11:54 pm, 26/6/2024] +91 72520 42256: import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView

class AppInfoAdapter(private val appList: List<AppInfo>) : RecyclerView.Adapter<AppInfoAdapter.AppInfoViewHolder>() {
    class AppInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val appName: TextView = view.findViewById(R.id.app_name)
        val appIcon: ImageView = view.findViewById(R.id.app_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.app_info_item, parent, false)
        return AppInfoViewHolder(view)
   }

    override fun onBindViewHolder(holder: AppInfoViewHolder, position: Int) {
        val appInfo = appList[position]
        holder.appName.text = appInfo.name
        holder.appIcon.setImageDrawable(appInfo.icon)
    }

    override fun getItemCount() = appList.size
}
