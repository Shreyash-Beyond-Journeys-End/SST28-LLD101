public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public boolean usesSubject() { 
        return false; 
    }

    @Override
    public boolean requiresPlusInPhone(){ 
        return true; 
    }

    @Override
    public void doSend(Notification n) {
       
        
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
}
