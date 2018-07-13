package com.jerry.poetry.controller;

import com.jerry.poetry.base.Result;
import com.jerry.poetry.domain.Game;
import com.jerry.poetry.repository.GameRepository;
import com.jerry.poetry.util.DateUtils;
import com.jerry.poetry.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    private final static Logger logger = LoggerFactory.getLogger(GameController.class);


    @Autowired
    private GameRepository gameRepository;

    @PostMapping("/uploadGame")
    public Result<Game> handleGameUpload(@RequestParam(value = "nick_name") String nick_name, @RequestParam(value = "game_info") String game_info, @RequestParam(value = "game_score") String game_score) {
        List<Game> gameList = gameRepository.findAll();
        for (int i=0; i<gameList.size();i++){
            Game game = gameList.get(i);
            if(nick_name.equals(game.getNick_name()) && DateUtils.getDay(System.currentTimeMillis()).equals(game.getDay())){
                return ResultUtils.error("该用户当天已提交预测信息");
            }
        }
        Game game = new Game();
        game.setNick_name(nick_name);
        game.setGame_info(game_info);
        game.setGame_score(game_score);
        game.setDate(System.currentTimeMillis());
        game.setDay(DateUtils.getDay(System.currentTimeMillis()));
        gameRepository.save(game);
        logger.info("预测信息提交成功!");
//        out.flush();//有了这个，下面的return就不会执行了
//        return "redirect:upload_info";
        return ResultUtils.ok(game);
    }

    @PostMapping(value = "/gameAdminList")
    public Result<Game> getGameList() {
        List<Game> gameList = gameRepository.findAll();
        return ResultUtils.ok(gameList);
    }

    @PostMapping(value = "/gameNickNameList")
    public Result<Game> gameNickNameList(@RequestParam(value = "nick_name") String nick_name) {
        String nick = nick_name.replaceAll(" ", "");
        List<Game> gameList = gameRepository.findAllByNick_name(nick);
        return ResultUtils.ok(gameList);
    }

    @PostMapping(value = "/getListByDate")
    public Result<Game> getGameListByDay(@RequestParam(value = "day") String day){
        List<Game> gameList = gameRepository.findAllByDay(day);
        return ResultUtils.ok(gameList);
    }

}
