import subprocess
import sys

from contextlib import contextmanager
import os

commands = [
    "\thelp -- Print help menu",
    "\tbuild -- Build the docker container",
    "\trun -- Run the docker container",
    "\tdev -- Build and Run the docker container\n"
]


def help_command():
    for i in commands:
        print(i)


def unknown_command():
    print("No command given. Please use one of the following commands:\n")
    help_command()
    exit(0)


@contextmanager
def cd(newdir):
    prev = os.getcwd()
    os.chdir(os.path.expanduser(newdir))
    try:
        yield
    finally:
        os.chdir(prev)


class ProjectBuilder:
    @staticmethod
    def build(args):
        if len(args) < 2:
            unknown_command()
            return
        try:
            directory = os.path.dirname(os.path.realpath(__file__))
            directory = directory[:-16]
            with cd(directory):
                print(os.getcwd())
                if args[1] == "build":
                    subprocess.call(["gradle", "jibDockerBuild"])
                elif args[1] == "run":
                    subprocess.call(["docker-compose", "up"])
                elif args[1] == "dev":
                    subprocess.call(["gradle", "jibDockerBuild"])
                    subprocess.call(["docker-compose", "up"])
                elif args[1] == "help":
                    help_command()
                else:
                    unknown_command()
        except KeyboardInterrupt:
            subprocess.call(["docker-compose", "down"])
            exit(0)
        except OSError as err:
            print("OS error: {0}".format(err))


if __name__ == "__main__":
    ProjectBuilder.build(sys.argv)
