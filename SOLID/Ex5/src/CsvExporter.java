import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {

    @Override
    public String getFormatName() { return "CSV"; }

    @Override
    public String getContentType() { return "text/csv"; }

    @Override
    protected String encodeData(String text) {
        if (text == null) return "";
        return text.replace("\n", " ").replace(",", " ");
    }

    @Override
    protected ExportResult generateExport(ExportRequest req) {
        
        String csv = req.title + "," + req.body;
        return new ExportResult(getContentType(), csv.getBytes(StandardCharsets.UTF_8));
    }
}