public class VerificarCaractere {

    public static boolean verficarCaractere(String s1, String s2) {

        // primeiro verificar se a palavra tem 2 letras
        if (s1.length() < 2 || s2.length() < 2) {
            return false;
        }

        // depois comparar as duas "últimas letras da palavra 1" e as "primeiras duas letras da palavra 2"
        String fimDeS1 = s1.substring(s1.length() - 2);
        String inicioDeS2 = s2.substring(0, 2);
        // alterando o número "2" na substring podemos variar a quantidade de letras comparadas.

        return fimDeS1.equals(inicioDeS2);
    }
}
