package cc.minetale.hub.util;

import cc.minetale.mlib.hologram.component.HologramComponent;
import cc.minetale.mlib.hologram.component.HologramLine;
import cc.minetale.mlib.npc.NPC;
import cc.minetale.mlib.npc.NPCInteraction;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.util.TriState;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.instance.Instance;

import java.util.function.Consumer;

@Getter
public enum HubNPCS {
    TEST_NPC(
            new Pos(2.0, 40.0, 2.0),
            new PlayerSkin(
                    "ewogICJ0aW1lc3RhbXAiIDogMTYxODg4MjI4MDU2MywKICAicHJvZmlsZUlkIiA6ICIxNzhmMTJkYWMzNTQ0ZjRhYjExNzkyZDc1MDkzY2JmYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJzaWxlbnRkZXRydWN0aW9uIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYyZDhmZDNhYTU2MTdiMWRhYzBhYWU5YzgxZjZkZDcwYWQ5M2E1OTk0MmY0NjBkMjdlNGQ1NWE1Y2I4OTE4ZTgiCiAgICB9CiAgfQp9",
                    "QqDyaAcUt/Mb25Xdyb8GCmQvT/01YPpZ122jetWQXFXPFHtRTLLkSrZNJ4E8Ur4H23U75e4eivj4e6zQWgMH4grCcopX5mF/C02j+8LqzVhpIWSwgYqXkcfnvUy5FGUwWrA4DjVA1bzHRqgFRc0YZ7cvrgQkDnR73CzswN8bqCSH0RJbe7ohjMInSdt8+IhX5vbzEi1oXDSnwB7kNx15lvHjcXwMyCpqGE4NYNYe9EJFB3wqECFuAjBRyCabsf5piO/MWFiq8O8hgPHpQ6HYOqCSFi5bO8UB/ezvkdKcTXkndPetSwe8csc0Ctmmw64WzYP1RgxUJ8Pz0FwxcusMdUJlquPdEa40vmgX9Z5vCB443nPOEoVX1X8HmQbJp3iG0HsXQH1BqPyFJ9RQupDnMb2TVEYHWw6wRsGqAjKzPcJXcxAOnp2LrGzdhg5AFcB+Ep4d6PRmciqLEfLysefx4MS9mFRMv5YOWRK4QnAjE7+gkUxxJcW/YcfO3rhb0LI5fMh7KidFxPtAUxUNp1/ZiAyaY368JIu1vvj+nMkaiklDUwx5wD0dv8jh0H2Zm6SUd0YI5NFuuH5YV7cL9S71jDA1C/IIjaxw2OMuQzCtKI5puBgWRdZ0HEjrutIfrwwTBE/TTX75O1XISL61CVtKXfBSPLRmJthyWDNoNOYI254="
            ),
            (interaction) -> {
            },
            HologramLine.of(Component.text("Eimoh V1"))
    ),
    TEST1_NPC(
            new Pos(3.0, 40.0, 3.0),
            new PlayerSkin(
                    "eyJ0aW1lc3RhbXAiOjE1Nzc5MTc4OTEwNzcsInByb2ZpbGVJZCI6IjdkYTJhYjNhOTNjYTQ4ZWU4MzA0OGFmYzNiODBlNjhlIiwicHJvZmlsZU5hbWUiOiJHb2xkYXBmZWwiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzcxMDFiOTIxYjg2YmIzMmQxYTQ1MDgzNTQ0ZjRmYzAzNmNiODNiYzE0NTlkZjc5NTFhYjc2ZjhiMDMwZTdkZTAifX19",
                    "SsIB/huqf/iLSyL5PA0mWfAYMlzTikryRgY/9HktGU39/lwyeUtV61TCSUxqjTix/FhnipjWfpHMoFhZ9V+6m4JtgLyVmFdizD2G25iqvu+yxMPUQI4XjCjRkXsYabQjwM6KqGaPChwfRS2z+g3XaU/EkNAttcOc3wGvMeOQjUdsKn1rYTKJ7FrNdaMHkWz2asEfQwzAlIuCoKTV/ZDEVb0GANJ58yp05rrfik4ovDPc6mRnvGSRHQH6yo7rrh+z5VkDmmlRWbNfyQSciqpzyU6hB+ioEZWMs4eDqxH4xmGL9BwZ1e8VUKSNs2/p/TsffCa0zkWSBKFB/1K352wF8fBDwpYF0qZ0SnTXaR22JwEBrRZLhCJlxzGlgiKqjTHBbF+3bOc+t6ai3L7PCFASe+7pFuezHkOMA58H9x529dPf+iB8sGdQ3v7kUhQitMKb86t6AhgOzGjwNqlt6NWmjAFlHIKw8XMDutg0O7TtZBTjY0orM2o5TLfBe/8yjPV8K3r115nK1v3kmuXGzyzcx5FLEBJM5clHruLUHL/EkhW05dL4UwFaO2lUJw9UrxaveiQ/fhnduSh5wC4wnRPOAcHgR1yGpdnvqDihwwKphbKm2S1zdu31v+r3utrl8T7K25o22fRS+acbj9BSyTuIyfTJ08liYkgtBLStgq6qKUA="
            ),
            (interaction) -> {
            },
            HologramLine.of(Component.text("Eimoh V2"))
    ),
    TES2_NPC(
            new Pos(4.0, 40.0, 4.0),
            new PlayerSkin(
                    "eyJ0aW1lc3RhbXAiOjE1Nzc5MTcxNDIwNDksInByb2ZpbGVJZCI6ImZkNjBmMzZmNTg2MTRmMTJiM2NkNDdjMmQ4NTUyOTlhIiwicHJvZmlsZU5hbWUiOiJSZWFkIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yN2MzMDExN2M5MDMwMDkzY2RmZjdjMTFmYWM4ZWJmNWI5YzBiYzAzZWE1MGE3NWQzNTZmMjRkZDYzMWU5ZDJlIn19fQ==",
                    "aF+MxlrOplL4cesEo+n6Id4yXhQp49Ibi/csrA+xBLvcb/91I+YPV1uqJbDNYmbCEZqKZB1mS4AnDAH6EPKiCDG7vjVy+l+Uap5itmysxbLhh4tAPZjnSYzVyPUGDO/rEo+Rs6z9Pju2qRCB34B8SIP6Mfyz+Nx4AWtqzw3P5dLvMV7Ak96PLnBlDTRFIh0VT50TChbAuWAHRUOwQAcXsrkMYck8qNSZrLEbVzeMR32MyBex4Nb9yIflBQunjCoOoF+at1bKmWkBTUH+budl3OwnIoSDrkLQZOHk6nu7JBjQyauTPyew1VIR7qBENAsFZErBcbvnzI7kuqazjwui0lxBU+jXrFRXWpmNWpdICUIsA5XPIib0hzR+UGr4oYM2fdssifCYIut5nhVJ3sv8b8Ixo7g+9QgM6e1pSeXXu+4Ld2+jcSVvXpiFh8DJq+1JqjmRMxg0TC5KWimWWUp1TLaJw4XA8NM8BTynBhfUmWbfLtRn2E+kRZLrXd43H8TlyVzADmRbd+JlDDVDEBASJ47Gl20cFAKzcMvUYyZTq2vRObvbEs6vocLnBU3NWCLiLVmCITSwzH8Gk0zWdsr9gDdCKhAnbdH4GyjsNiZD0QuWDgiplXRkn/yOaIlq4sDWmMG7GU+TlzoSQLrZ5roBfPu7SHKsPRK8yZLXvzNiFGE="
            ),
            (interaction) -> {
            },
            HologramLine.of(Component.text("Eimoh V3"))
    ),
    TEST3_NPC(
            new Pos(5.0, 40.0, 5.0),
            new PlayerSkin(
                    "eyJ0aW1lc3RhbXAiOjE1Nzc5MTM1NjQzMTQsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jZjY1YzE5MDFiNmE3ZjEwNDAwZjBiMjc3MTNhZDU3ZGI2M2YxMGQ2MTgyOGUxOWVhODBjZWVjYzRiZTIzMjFiIn19fQ==",
                    "bFFvvuNAhn/YBgV8eDUD0UxNg43DjEuJVde8vmZAHx4PqBn/Wf7kbdwveFuLe8zSFIbM8n7+LB21u4yZQNLD3XmJsL6R9hRjQFGwyjXsmXLp8V69sGvMV0D4EiWoOAG6V9v+1npVxylWfsePQifwrBB5UHYYBDZAzc+NRVxhlk8TmNaWPYml/dF7gZXE27i6GVo6INSTrNv393B7ad8+oDx7NbeMxyMY9/HeRkS12vDciOLWwsAQ4HdvmtQs00M+L8W1Oa/eY+8IzVNUtmVwDXfSHYgkwWzemy8qX3WNMf8+z5nl52uXr3ePtshS4lksTS4bSA8C3g85c/YMyn2+PnyfET1KCDMWotOt24x4j78B3bjWVLglwMwl5aED1PvzNze3ssGFX0SWd/fLl0sngKh6n3nT2gFn2nCgQIWX7D3SC0WHmwN8C3A0sqSED6XjnfFjWzJjvE2lIsQwzTtjW+xeQsbgHvzQkd+MF+KJZ5/xY/2eYv8Gga79zrGdewlnkBIu+E5DUBLnSnNjVJJCCkWYU71Id16sboTw3kCqHqunhxJdlvsLWLxTdOSKQ+7F+3tsf6dxjGDrzLVOdVcPR5mTC4DML3H0alv5BvzmLrbGBo5osHV5muq8QjolwSnzcu2FJDD21oskEBarAY7ZHDBj6HUL5PGtxQ6zdTXFP1A="
            ),
            (interaction) -> {
            },
            HologramLine.of(Component.text("Eimoh V4"))
    ),
    TES4_NPC(
            new Pos(6.0, 40.0, 6.0),
            new PlayerSkin(
                    "ewogICJ0aW1lc3RhbXAiIDogMTYzNTQ1Mjg3NjIwNSwKICAicHJvZmlsZUlkIiA6ICJlMDRhZWE0ZTc4ZGE0ODlhYjFiMDE3MDI2NjVmYzlhMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJQSU5LX0dVTU1ZX0JFQVIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmNkMTYzYmVmYzhlODUwMzVlNTU1NGY0MzY5MWY4ZTdmZWVkMzYxN2RjMjNmOTg0MTNjZGQ0YmQ3N2U0NzQzNiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9",
                    "NWIep4dS78B+2Sh59SjCzeOjjqaN6Du+MF5NKmEzh1UFKenae9CHaGhl8cXis2ps8xET/gbS6bw2E9gebhjEy0hA+EtXMXOo8dgq5nHNFPDywj0PgcCh53rgZlyJ+vCHj3l9aBbZ3J7FzGYW4B/IM2EDQRkH4m66AcF3s0FmXIkBVuF3hy+Nj6qyeUJrgvee2SR5M71boa2WqEhV/YS/DhsF81sJsDEg2sOG4gdLNDfakx57KjJLzZYZWcCh3fRDUqy4JEvt644xQVcrmuunzdIGqN7FI2vjM3qyM1IKFqHUMAjawTK7ZwYk38AvoDtnweQR2hgLJkw4oVA+cJ+anlILZ9dZ68vcfJTnOdO1h3oC6z34Ydmc6aneSxnA9oZA1cuUdNuQ/4U3JZPQdvrHu1MKxfxCXZSd63uzVlQKwBkFXxFjs3193EceI1swip3vQMT1sF2X5FE4jA//ALrWNqVnhEUjWpqX10TWRqNyixSIDYmHGfD3foCypJKqV1Gei96HcENHQTTDyQ14VdIEv9AnGQyBY2Nrjc9ycJ3FhvoUUVLap/jF1zUSQ1x44nKA6I0ug4Hi/lEf/OY/TAPiw9/519+8oNcucBabBFOaIfJXLpMKXsgmKWOmUzV0xwD6IEWvt93eJh48DoK3inqvx9AL9BW0cvNWl+sWzckHxXs="
            ),
            (interaction) -> {
            },
            HologramLine.of(Component.text("Eimoh V5"))
    );
//    TES5_NPC(
//            new Pos(7.0, 40.0, 7.0),
//            new PlayerSkin(
//                    "ewogICJ0aW1lc3RhbXAiIDogMTY0OTEwNzYyNzg3OSwKICAicHJvZmlsZUlkIiA6ICI5MTEyMWQ4ODhhMDc0YmVmYmM3OGM4MDQ4NGUxYjZiNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJCeXRlQ3JhY2siLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTkyM2YyZGYzYzJhYmYwMDkwNzliMDNmMTI5MTFhZDBjOWI2ZjM4YTYyMmJhY2M5MWJlYmViNDU3ZjM4MzNiNiIKICAgIH0KICB9Cn0=",
//                    "M0PIdfojvRzdYIOvsUKwJuFuQ3O69/LWPKAad+fcrYY4ac8J3O0jIrQvjA5EV3A8iLyAzBZKf8rd6FSmWWNsEu+yiDUfGe7F1DPLfXqU30auiXHonw3vgAzD738Uh///pNx+A3BTOCFMhu/WCsImTnhNgb2h7rfr5PS1lFV6fTYufeCbObpcwqGCGIzzgxRZuWOdscpGG9hvGClXABM3dYtYdkpxrRDf1Yo0lM2QooJHgUceGgE531+yZ7GxCHv1SKZRytANesomYF4r5UA42pBb0q64hcDyQL1k0a9RVyqfN5b0xNh6/CPQ89Eu+1+f5UfCt4V/uYqStNsSwwfauTuXIlSs7QbnKeqx1bwa3wPfPJH+efOcXQNVBQPdVdBckAppj/HAokIfwZVsOn1WcW2URANhg+xVPyo85IHbUHY/ZppDonuNjsIrrSY9ZodarESmrh9Oi0twMGIBEpNzY6gRMuyC5qzHFqk1Z+TC2ayGraiJAenAJPhYPKs1VNYADT5TqmNFnJvt7PWTFiYmW+cRuM1wEndCkJ3F6bd9PNRdNOSVnZHI3EP68Kwqs7WlqtmU1d6bB94vTE7pp7riPMW2gjYflTJ+JzL4JVec5Sf2NCvSpRrjkjz10dL7Sz+G92vyqTx3zjOh4V1QFTtndAFRUVyrxdRjSakLG4ahWoM="
//            ),
//            (interaction) -> {
//            },
//            HologramLine.of(Component.text("Potential Bottom", NamedTextColor.AQUA))
//    );

    private final Pos position;
    private final PlayerSkin skin;
    private final Consumer<NPCInteraction> interaction;
    private final HologramComponent[] components;

    HubNPCS(
            Pos position,
            PlayerSkin skin,
            Consumer<NPCInteraction> interaction,
            HologramComponent... components
    ) {
        this.position = position;
        this.skin = skin;
        this.interaction = interaction;
        this.components = components;
    }

    public NPC spawn(Instance instance) {
        return new NPC(instance, this.position, this.skin, TriState.TRUE, this.interaction, this.components);
    }

}
