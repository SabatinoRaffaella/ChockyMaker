package Filter;
public class Filter {  
    public String Filter(String input){
        StringBuffer filtered = new StringBuffer(input.length());
        char c;
        for(int i=0; i<input.length();i++){
            c=input.charAt(i);
            switch (c) {
                case '<':
                    filtered.append("&lt;");
                    break;
                case '>':
                    filtered.append("&gt;");
                    break;
                case '"':
                    filtered.append("&quot;");
                    break;
                case '&':
                    filtered.append("&amp;");
                    break;
                default:
                    filtered.append(c);
                    break;
            }
        }
        return filtered.toString();
    }
}
