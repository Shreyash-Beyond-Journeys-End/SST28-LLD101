import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter {

    @Override
    public String getFormatName() { return "JSON"; }

    @Override
    public String getContentType() { return "application/json"; }

    @Override
    protected String encodeData(String text) {
        if (text == null) return "";
        return text.replace("\"", "\\\"");
    }

    @Override
    protected ExportResult generateExport(ExportRequest req) {
        String json = "{\"title\":\"" + encodeData(req.title) + "\",\"body\":\"" + encodeData(req.body) + "\"}";
        return new ExportResult(getContentType(), json.getBytes(StandardCharsets.UTF_8));
    }

    
}