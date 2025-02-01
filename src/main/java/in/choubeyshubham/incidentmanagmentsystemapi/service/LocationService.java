package in.choubeyshubham.incidentmanagmentsystemapi.service;


import org.springframework.stereotype.Service;

@Service
public class LocationService {
    // In a real implementation, call an external API to get the location by pincode.
    public String[] getLocationFromPinCode(String pinCode) {
        // Dummy values; you can add logic such as: if(pinCode.startsWith("560")) -> Bangalore, India.
        String city = "DummyCity";
        String country = "DummyCountry";
        return new String[]{city, country};
    }
}
