package com.queuesystem.messageParser;

public class RequestFreeResourcesMsg implements MessageSender {
    @Override
    public void send() {
        System.out.println("Message sent:");
        System.out.println("""
                <MessageDateTime>20242202T00:24</MessageDateTime>\n
                <MessageType>Request</MessageType>\n
                <RequestFor>FreeResources</RequestFor>""");
    }
}
