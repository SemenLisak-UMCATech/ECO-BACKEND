package com.example.ecomon.service.calculation;

public class CalculationService {
    public static double calculateHQ(double pc,double rfc) {
        return calculateAddLadd(pc)*rfc;
    }

    public static double calculateCR(double pc, double sf) {
        return calculateAddLadd(pc)*sf;
    }

    public static double calculateAddLadd(double pc) {
        final double defaultBW = 60, daysInYear = 365,
                defaultTout = 1.64, defaultTin = 19.69, defaultAT = 70,
                defaultVout = 0.63, defaultVin = 0.5, defaultED = 70;
        return (((pc*defaultTout*defaultVout)+(pc*defaultTin*defaultVin))*daysInYear*defaultED)/(defaultBW*defaultAT*daysInYear);
    }

    public static double calculateAirFee(double mfr, double pv, double tlv) {
        final double defaultMinWage = 6700, defaultKt = 1.55*1.25, defaultKzi = 1, t = 30744;
        if (pv - mfr <= 0) return 0;
        else if (tlv > 1)
            return getPollutionMass(mfr*3.17 * 0.00028, pv*3.17 * 0.00028, t) * defaultMinWage * (10/tlv) * defaultKt * defaultKzi;
        else
            return getPollutionMass(mfr*3.17 * 0.00028, pv*3.17 * 0.00028, t) * defaultMinWage * (1/tlv) * defaultKt * defaultKzi;
    }

    private static double getPollutionMass(double mfr, double pv, double t) {
        return 3.6 * Math.pow(10, -3) * (pv - mfr) * t;
    }
}
