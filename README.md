 PhonePe SDK Integration

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

