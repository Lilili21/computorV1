NAME=computor

all: $(NAME)

$(NAME):
	@gradle build
	@cp build/libs/computor.jar ./$(NAME)

fclean: clean
	@rm -f $(NAME)

clean:
	@rm -rf $(NAME) ./build/

re: fclean all

.PHONY: all fclean clean re lib