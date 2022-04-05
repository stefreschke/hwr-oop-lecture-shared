# HWR OOP Lecture Shared Code

This repository is used to share source code material for an ongoing lecture on object-oriented programming with Java at HWR Berlin (summer term 2022).

> :warning: This code is for educational purpose only. Do not rely on it!

## Students That Are New To Git

It is best to have a local repository of this at hand. To avoid Git additional git confusion, follow the following steps:
1. Make sure you have the required software available (IntelliJ IDEA, JDK, Maven, Git).
2. Clone this repository into a directory of your choice.
```
git clone <repository-url>
```
3. Copy the repository's content into another folder next to the repository. Then, in the other folder, remove the .git-folder. The following commands should do it. If you are using Windows without WSL, do it all by hand in the explorer (just as you are used to).
```
cp -r <repository-name>/ <other-folder-name>/
cd <other-folder-name>/
rm -rf .git/
```
4. Open both the repository and the other folder in IntelliJ IDEA.

> :warning: Only code in the other folder, not the cloned repository. You should only use the cloned repository to copy code into your local environment.

5. Once you are required to refresh the code during the lecture: Go into your local repository (the original, not the copy) and pull.
```
cd <repository-name>
git pull
```
6. If the pull fails (again make sure that your code is in the other folder, the copy created earlier), do a "force pull".
```
git reset --hard origin/master
```

## Git-aware Students

Feel free to fork this repository and do your coding there.
Remember to add this repository as a second remote repository (upstream) and pull from the correct remotes.
The following section describes how to do this.

### Multiple remote repositories

Your local repository should have a reference to both the fork (your own remote repository) and the original remote repository.
To configure your git remote repositories, use the `git remote` command set.

1. Clone your fork and go enter the repository.
```
git clone <fork-url>
cd <created-folder>
```
2. Now your fork is configured as primary remote repository (origin).
Next to origin, you should add the original repository as a second remote repository (upstream).
```
git remote add upstream <repository-url>
```
3. Verify that both remotes are configured correctly.
The following command should list both remotes: origin and upstream.
```
git remote -v
```
4. To fetch changes from all remote repositories, use:
```
git fetch --all
```
5. If there are interesting changes (in e.g. the `main` branch) to merge into your branch, use:
```
git pull upstream main
```
