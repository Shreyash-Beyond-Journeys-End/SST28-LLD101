public abstract class Exporter {
    public abstract String getFormatName();
    public abstract String getContentType();

    public int getMaxContentLength() {
        return (int)1e9; 
    }

    protected String encodeData(String text) {
        return text == null ? "" : text;
    }

    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            return new ExportResult(getContentType(), new byte[0]);
        }
        

        if (req.body != null && req.body.length() > getMaxContentLength()) {
            throw new IllegalArgumentException(getFormatName() + " cannot handle content > " + getMaxContentLength() + " chars");
        }
        
        ExportRequest safeReq = new ExportRequest(
            encodeData(req.title), 
            encodeData(req.body)
        );
        
        return generateExport(safeReq);
    }
    protected abstract ExportResult generateExport(ExportRequest req);

   


    
}
