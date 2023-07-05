package com.example.localmap.itens_listas;

import com.example.localmap.R;
import com.example.localmap.recycler_view_classes.Categoria;
import com.example.localmap.recycler_view_classes.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

public class ItemEstabelecimento {

    public static List<Estabelecimento> criarEstabelecimentos() {
        List<Estabelecimento> listaEstabelecimentos = new ArrayList<Estabelecimento>();
        int quantidade = 50;
        for (int i = 1; i <= quantidade; i++) {
            Categoria categoria1 = new Categoria("Supermercado" + i);
            if (i % 2 == 0) {
                Estabelecimento estabelecimento = new Estabelecimento("Nova Vida" + i, categoria1,
                        "Avenida Ministro - Av. José Américo, 480 - Santa Luzia, PB, " + i + "000-000",
                        "rota não definida",
                        "https://lh3.googleusercontent.com/fife/APg5EObjQXbpQMb9np0qlkSKyoYjT6J5ol31Y8lVN39sX9mpD8CJ_MpwSR91HMaPNNnOqnSRGasxA6OPJr5p1THl-4kziMl_qUtX4Fkrp9DB4ofK6isYUXJVZ3qCgS1NFqeWzZw9dqrvvXB7o-lzbFIRYdNMhzhTe-PqWUzgisBqJDU6dlH379CcJuJcfBGnTAzY02IuGBUC5DhonD2WbWO5UplRhTUqrCH3VfkThL6Uo4zfnnaTC7OuZkxfPZOMZ2RqcibAu2ulUPuKCuBMMuCeWSqY-D721uSHyNlIeSfaInua_hSYeog5kctunOlRqhW4nbL89UL2wgtr4g0usbKZ-sME-VXAkYXsTq5_BDr1ZQ5bE7ADwaFDyvzZhLlGGhTOJ6DGm_cmuIBQHUt0sYrsNaxQxnAXllVRkjgmwTao-VahWQSza4BvMsD3--wYfJ0bOJQRQzjQcGzdeVKWhOeeEfLegNLZ9NA-vubiMa6UXzfLNODRCzo8P16_d-rm_K24RlahaV0Nk6BcFtyVRwa2qMYfplT-gP3nKDWAXNrIJhnXbZlzQflI30k5cXlWhAD0DUte9TOdo8d5IwsS3RwYYebUvk8xb5eB26lIeKNY5ay5b5a8SYKdem8OqjKPiHA5_OAOcU-6e4ylN7_ng4Ou6cSYQF_8zgUMtGDUghitVELWiUGfKDhyf5iW8bl0QnmFT0OSatoj3U42EUgNE6guzV6lOdK1KYBiXcubqdp7XvbDjGPwJBxxZDv1nmS6ofidGNVmodzYGKCb8fGt_5aN-OLvawBxfrnXY3vzWIsfIwxdWJkVbY_vnJN9dj6fb6s0hap_F1XEHPIetbp5ye0gtFAlqZfLlOvW8_uCl8uwR50HC-Voiw8d8XrhXYw8NagyVlcbusFZqufyc2BLkLGJuZ_dgg-5lVmjBCF_nhA6t1N5gzm9ipNcRTAiJxexdcXNBB2trEzCPgO0KEYoN-MOJ5CucCSN7BV6TwmlyobmntEknWRVWSaFjet0ieqz70SnYo7ZR0i_8o3xQlk080Jqbq3Qg0CbwjLgU0yCsjIuBPxBUVe75pgVKCIoDmm-eyLZi8vWvVp2Rpqr7RksuC0b3uPusrkc_1LciQp0sFcznWC0C4D1EK-CIhq6vRUNK9jty7rzNrkl0Z-_bgPxEv-4fWG0WVLyb6cMXBpZizmfkMo6SGEAGYSRYGUEhsz716p2PVnWU3k0-7ZpVNzurgKfqwdTIXMan3MX0S1IMwhy6tAEKYigCzsFvUhatRXqgw4jfZZe8rmEQLGFw5yqdF5nBq4KV_vmH0Q3WbTYgC4avdPJGL44Z8xL-1oVKWmrSiPx3TDxFBoqg0_XvvA1cy38Ho5Ilg0gltfWQDuaPd0p-fow7bkhZuqmw_XCTdWPpzOLisPGpFOgmSo3nVUnAdnCADI0QrpY3wdgjfE6EPCbzVTPE3OglTucvPAD33O6cZrltGHwmbieAUqVPa89VEqT8X6rb5kTqTDjA3g9JA3VNN1RU15Dyeg5cNELyGKi65m1YHkP7Q6f8aYPLyrc3Pz3dfX9BhjG8fUtZvAJBw78lz5xFuXrWDB1cQ1cHAHQPxIobNal1vR1w57XqPs=w1366-h663",
                        5.0 - (0.2 * (i/2)),
                        "(" + i + ")334612728", false);
                listaEstabelecimentos.add(estabelecimento);
            } else {
                Estabelecimento estabelecimento = new Estabelecimento("Nova Vida" + i, categoria1,
                        "Avenida Ministro - Av. José Américo, 480 - Santa Luzia, PB, " + i + "000-000",
                        "rota não definida",
                        "https://lh3.googleusercontent.com/fife/APg5EOb_0ccsObeGJRdZ0UznKeKnqaDoGIUWmkWReORZmCniO3TighuqaNxB_H_Pfheyul_L_OIxbFJ-Dm3w-lO7k7h-dgBSIRKuVDfC5DEf5JfUKB65cNxWWyD1UJuq8J5GZQZ0Jl_ej8TLc_21p3-2Xvm5HKnuEjwzGRWr0ruYTm1QZYyw7iiBClggvsom4meNXWYS2MRjB6jVwFj4XH6fmlj5a1g80CeOxd6qHJ68tfZn-ud-bfSIrDqLPBDyZmI_kPBjFMF_bl2LXJ-1LwW_eTtgpQkVuQb2I878tTj9aHBdGkz7NCxwI6KEUH3T3Z8LnWP0C4bZJ7rpaReEDcNoN3sfb_rLz9R-sIhsGYVU4V2UWuctk3k1tWke3g3s3qRbrpJyhPrMqvX7HwPL8LvNIDo0eTi5D4junNy-tVNnnWELKXTpvKV-ZxrwBxEJ9LpXaiGpOr_HUwk1j0rake99L8fqY5npCh8q-FCXJTreofQ3rLfE3S4r2n8nGU3TiE4l0v6uWPBG0gRy2OP1QWseIc5v-VEhM4V-OaQqK4F_QU_2CL1GmkbMAMBOn7-qOYmh9gVGgpp6er1VI9xf-PwnPvCAM2Q23jiIbfwZPzxNH9VRgdXCnEvKfAU7DSFT6oOxowMUdeKWrSud_6UYvw_ZexkcCMuU33Mc3jykKy7aR3aaKUWCm0XMmaUZMYOUYh9FfZ3rbtvlKtIt-ZmOD09q3cFYTVIHu8EbeS0n293UYH7vHC8UbXUJ7jgJWKKu_UNJhkdNWyvzm_OVt8hiPoovfkjmkHfVaixEApcBXnqprZy6uwQk_rfbdFyyZDBshDByI-vUIaDcO8rfQpiOiwURSK1Sk48bstyTmghDDHjjQLuQZVrEvKF-ObCnkDfizNmcYu2OpbvKoVKSLfz8m7VGmkSvLcmsaWSjIKgN989EDtax9Wmeb9ZCWp0pV6xAEbLmme1ga23g9ShmfDsaZwTAbRysmcPZ_oQdy_H_d_Zq8E76BzUjMipsZIGcWW02pIQXJJwtkQX1kb9inv6IopI4btaq7RZ8Aaa27p78r2483bGQ3yMIznqJ8mJ6_ChoWKp3lhTuvz6HZpSqbh-isfkp1mEfhMBj21LJMdRY2Jvk_00zRdvQHNPBx-Ih5pGG4WxbX33WogzA5IYmdiZpfjrxf_5ukJt7Tqt3FjeYdIXtW9ZydFBIsb2yTBI00A3jwUZmu63RM_qKmFR1jpMQ2s7hR2PkjF6A3oYSz7AXZTLwwCofk9eNqZM2w8LViLEvXwvmiQVg-NqMozWl0Q8MVihE4kXxmWgGcxBJIhzte-VARqNYdaPEQ3Jxhgqk8P8bkST_56FxaWSDpr-Y5LiaFfN6UUov5MqxdRUvvceDRNW4Gjx59Td7Q0K2M4sed0aJHtH8auDYIr8q8FkLz_g1lo-pXdyF02dBEkcehfum0P2wd8Cb1kStnEouiKzRSHCGY1I6UU7aeOcKulQdW3t7nwE5JIYiWZ2NkhgzoUK_1xhaZ80oj79KlW83qkHZJBHPMqn8rmKDw-aJ0tAsxuvipRk7A_LivXVqO4zq4L6OG0iozV8eQxtgq_xw5K5NBQ2URJ7jz7s4K1YmWrSQOsE=w1366-h663",
                        5.0 - (0.2 * (i/2)),
                        "(" + i + ")334612728", true);
                listaEstabelecimentos.add(estabelecimento);
            }
        }
        return listaEstabelecimentos;
    }
}