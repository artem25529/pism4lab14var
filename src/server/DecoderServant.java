package server;

import coder.DecoderPOA;
import org.omg.CORBA.ORB;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class DecoderServant extends DecoderPOA {
    private ORB orb;

    @Override
    public String decode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void decode(String newDecode) {
        final byte[] buffer = Base64.getDecoder().decode(newDecode.getBytes());
        try {
            Files.write(Paths.get("file.txt"), buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOrb(ORB orb) {
        this.orb = orb;
    }
}
