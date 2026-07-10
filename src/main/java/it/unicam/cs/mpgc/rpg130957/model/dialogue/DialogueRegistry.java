package it.unicam.cs.mpgc.rpg130957.model.dialogue;

import java.util.List;

public class DialogueRegistry {

    public static final Dialogue DRUIDO_INTRO =
            new Dialogue("Druido del Bosco", List.of(
                    "Benvenuta, viandante.",
                    "Il bosco è inquieto… qualcosa si sta risvegliando.",
                    "Raccogli ciò che puoi, e preparati."
            ));

// non utilizzato, per implementazioni future

    public static final Dialogue DRAGO_FINALE =
            new Dialogue("Drago Primordiale", List.of(
                    "Hai osato disturbare il mio sonno millenario.",
                    "Mostrami la tua forza, strega."
            ));
}
