package study.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import study.model.CricketTeam;
import study.repository.CricketTeamRepository;
import study.utilities.Compress;
import study.utilities.Decompress;

@CrossOrigin() 
@Controller
public class CricketTeamController {

	@Autowired
	private CricketTeamRepository cricketTeamRepository;
	
	@PostMapping("/upload")
	@ResponseBody
    public String uplaodImage(@RequestParam("image") MultipartFile file,
    		@RequestParam String playerName)
            throws IOException {

		CricketTeam player = new CricketTeam();
		player.setPlayer(playerName);
		player.setFileName(file.getName());
		player.setFileType(file.getContentType());
		player.setImage(Compress.compressImage(file.getBytes()));
		
		cricketTeamRepository.save(player);
		
        return "Image uploaded successfully: " +
                        player.getPlayer();
    }
	@ResponseBody
	@GetMapping("/download")
	public CricketTeam getPlayer(@RequestParam int id) {
		
		Optional<CricketTeam> obj =  cricketTeamRepository.findById(id);
		CricketTeam player = new CricketTeam();
		if(obj.isPresent()) {
			player.setPlayer(obj.get().getPlayer());
			player.setFileName(obj.get().getFileName());
			player.setFileType(obj.get().getFileType());
			player.setImage(Decompress.decompressImage(obj.get().getImage()));
			return player;
		}
		
		return null;
	}
	
	@GetMapping(path = {"/download1"})
    public ResponseEntity<byte[]> getImage(@RequestParam int id) throws IOException {
    	
    	
    	
        final Optional<CricketTeam> dbImage = cricketTeamRepository.findById(id);
        if(!dbImage.isPresent()) {return null;}
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getFileType()))
                .body(Decompress.decompressImage(dbImage.get().getImage()));
    }
}
