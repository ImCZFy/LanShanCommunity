package lanshan.imczfy.lanshancommunity.objects;

public enum Channels {

    Public, Art, Staff, Admin;

    public static String getString(Channels channel) {
        switch (channel) {
            case Public:
                return "public";
            case Art:
                return "art";
            case Staff:
                return "staff";
            case Admin:
                return "admin";
        }
        return null;
    }

}
