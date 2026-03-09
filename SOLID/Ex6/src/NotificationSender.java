public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }

    public int getLimit(){
        return (int)1e10;
    }

    public boolean usesSubject(){
        return true;
    }

    public boolean requiresPlusInPhone(){ 
        return false; 
    }

    public void send(Notification n){
        if (requiresPlusInPhone() && (n.phone == null || !n.phone.startsWith("+"))) {
            throw new IllegalArgumentException("phone must start with + and country code");
        }

        String body = n.body;

        if (n.body.length() > getLimit()){
            body = body.substring(0, getLimit());

            n  = new Notification(n.subject, body, n.email, n.phone);
        };

        doSend(n);
    };


    abstract void doSend(Notification n);



    
}
