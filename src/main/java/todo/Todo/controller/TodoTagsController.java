package todo.Todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.Todo.domain.request.TodoTagsRequest;
import todo.Todo.domain.response.TodoTagsResponse;
import todo.Todo.service.ITodoTagsService;
import todo.common.constants.ApiConstants;
import todo.common.domain.response.AppResponse;

import java.util.List;

@RestController
@RequestMapping(value = ApiConstants.BASE_URI + ApiConstants.TAGS_URI)
public class TodoTagsController {

    private final ITodoTagsService todoTagsService;

    public TodoTagsController(ITodoTagsService todoTagsService){
        this.todoTagsService = todoTagsService;
    }

    @PostMapping("/create-tag")
    public ResponseEntity<AppResponse<TodoTagsResponse>> createTag(@RequestBody TodoTagsRequest todoTagsRequest){
        return ResponseEntity.ok(todoTagsService.createTag(todoTagsRequest));
    }

    @GetMapping("/get-tag")
    public ResponseEntity<AppResponse<TodoTagsResponse>> getTags(@RequestParam String tag){
        return ResponseEntity.ok(todoTagsService.getTag(tag));
    }

    @GetMapping("/get-all-tags")
    public ResponseEntity<AppResponse<List<TodoTagsResponse>>> getAllTags(){
        return ResponseEntity.ok(todoTagsService.getAllTags());
    }

    @PutMapping("/update-tag")
    public ResponseEntity<AppResponse<TodoTagsResponse>> updateTag(@RequestParam String id, @RequestParam String tag){
        return ResponseEntity.ok(todoTagsService.updateTag(id,tag));
    }

    @DeleteMapping("/delete-tag")
    public ResponseEntity<AppResponse<String>> deleteTag(@RequestParam String id){
        return ResponseEntity.ok(todoTagsService.deleteTag(id));
    }


}
