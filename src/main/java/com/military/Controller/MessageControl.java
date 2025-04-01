
package com.military.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.military.DTO.InboxMessageDTO;
import com.military.DTO.SearchRequestDTO;
import com.military.DTO.SendMessageDTO;
import com.military.Entity.Message;
import com.military.Exceptions.MessageNotFoundException;
import com.military.Service.MessageService;

@RestController @RequestMapping("/api/message")
public class MessageControl {
    
	@Autowired
	private MessageService messageService;
	
	 // Constructor Injection
	public MessageControl(MessageService service) {
		messageService = service;
	}
	
//	Message operation methods
	@PostMapping("/send")
	public ResponseEntity<String> sendMessage(@RequestHeader("Authorization")String token, @RequestBody SendMessageDTO message) {                                          
//        Send the message using the service
        boolean success = messageService.sendMessage(message);
        return success 
        		? ResponseEntity.ok("Message sent successfully") 
        		: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending message");          
	}
	
	@GetMapping("/inbox/{userId}")
	public ResponseEntity<List<InboxMessageDTO>> fetchInboxMessages(
			@RequestHeader("Authorization")String token, @PathVariable int userId) {
		 List<InboxMessageDTO> messages = messageService.getInboxMessages(userId);
		 return messages.isEmpty() 
				 ? ResponseEntity.noContent().build() 
				 : ResponseEntity.ok(messages);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Message> getMessageById(@RequestHeader("Authorization")String token, @PathVariable int id) {
		Message message = messageService.getMessageById(id);
		return message != null
				? ResponseEntity.ok(message)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@PutMapping("/read/{id}")
	public ResponseEntity<String> markMessageAsRead(@RequestHeader("Authorization")String token, @PathVariable int id) {
        boolean result = messageService.markMessageAsRead(id);
        return result 
        		? ResponseEntity.ok("Marked as Read")
        		: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed marking message as read");       
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteMessage(@RequestHeader("Authorization")String token, @PathVariable int id) {
		boolean result = messageService.deleteMessage(id);
	    if(result)
	    	return ResponseEntity.ok("Message deleted Successfully.");
	    else
	    	throw new MessageNotFoundException("Failed to delete message.");
	}
	
	@GetMapping("/search")
//	method for search functionality
    public ResponseEntity<List<Message>> searchMessages(@RequestHeader("Authorization")String token, @RequestBody SearchRequestDTO searchRequest){
         List<Message> messages = messageService.searchMessages(searchRequest.userId(), 
        		 												searchRequest.keyword(), 
        		 												searchRequest.startDate(),
        		 												searchRequest.endDate(),
        		 												searchRequest.readStatus());

//       For Debugging purposes.
         for(Message msg : messages) {
        	 System.out.println(msg);
         }
         return ResponseEntity.ok(messages);
    }
	
}

















