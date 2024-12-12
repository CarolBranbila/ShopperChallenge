package br.com.estudo.bora

import com.google.android.gms.maps.model.LatLng
import kotlinx.serialization.Serializable

@Serializable
data class RideEstimateResponse(
    val origin: Location,
    val destination: Location,
    val distance: Int,
    val duration: Int,
    val options: List<Option>,
    val routeResponse: RouteResponse,
)

@Serializable
data class RouteResponse(
    val routes: List<Route>
)

@Serializable
data class Route(
    //val legs: List<RouteLeg>,
    //val distanceMeters: Int,
    //val duration: String,
    //val staticDuration: String,
    val polyline: Polyline,
    //val description: String,
    //val warnings: List<String>,
    val viewport: Viewport,
)

@Serializable
data class RouteLeg(
    val distanceMeters: Int,
    val duration: String,
    val staticDuration: String,
    val polyline: Polyline,
    val startLocation: Location,
    val endLocation: Location,
    val steps: List<RouteStep>,
    val localizedValues: LocalizedValues
)

@Serializable
data class RouteStep(
    val distanceMeters: Int,
    val staticDuration: String,
    val polyline: Polyline,
    val startLocation: Location,
    val endLocation: Location,
    val navigationInstruction: NavigationInstruction,
    val localizedValues: LocalizedValues,
    val travelMode: String
)

@Serializable
data class Polyline(
    val encodedPolyline: String
)

@Serializable
data class NavigationInstruction(
    val maneuver: String,
    val instructions: String
)

@Serializable
data class LocalizedValues(
    val distance: LocalizedText,
    val staticDuration: LocalizedText
)

@Serializable
data class LocalizedText(
    val text: String
)

@Serializable
data class Viewport(
    val low: Location,
    val high: Location
)

@Serializable
data class Location(
    val latitude: Double,
    val longitude: Double
)

@Serializable
data class Review(
    val rating: Int,
    val comment: String
)

@Serializable
data class Option(
    val id: Int,
    val name: String,
    val description: String,
    val vehicle: String,
    val review: Review,
    val value: Double
)
