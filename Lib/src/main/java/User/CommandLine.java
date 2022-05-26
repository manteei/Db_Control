package User;

import Collection.LabWork;
import DataBase.DBManager;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс управления коллекцией
 */
public class CommandLine {
    private ArrayDeque<LabWork> collection;
    private LocalDate date;
    private ArrayList<LabWork> collectionArr;

    public CommandLine(){
        collection = new ArrayDeque<LabWork>();
        date = LocalDate.now();
    }

    public ArrayDeque<LabWork> getLabworks(){
        return collection;
    }


    /**
     * Метод вывода справки о доступных методах
     */
    public String help(){
        return
        "help : вывести справку по доступным командам\n" +
        "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
        "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
        "add {element} : добавить новый элемент в коллекцию\n" +
        "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
        "remove_by_id id : удалить элемент из коллекции по его id\n" +
        "clear : очистить коллекцию\n" +
        "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n"+
        "exit : завершить программу (без сохранения в файл)\n"+
        "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n"+
        "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n"+
        "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n"+
        "min_by_minimal_point : вывести любой объект из коллекции, значение поля minimalPoint которого является минимальным\n"+
        "filter_by_minimal_point minimalPoint : вывести элементы, значение поля minimalPoint которых равно заданному\n"+
        "filter_greater_than_minimal_point minimalPoint : вывести элементы, значение поля minimalPoint которых больше заданного\n";

    }

    /**
     * Метод вывода информации о коллекции
     */
    public String info(){
        return
       "Тип коллекции: ArrayDeque\n" +
        "Дата и время инициализации: " + date + "\n" +
        "Kоличество элементов: " + collection.size() + "\n";
    }

    /**
     * Метод выводит элементы коллекции в стороковом представлении
     */
    public String show(){
        if (collection.size()==0){
            return "Коллекция пуста!";
        } return collection
                .stream()
                .map(LabWork::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
    * Метод добавляет элемент в коллекцию
     */
    public void insert(LabWork labWork){
        collection.add(labWork);
    }
    public void update(LabWork labWork, int oldId){
        labWork.setId(oldId);
        collection.add(labWork);
    }

    /**
     * Метод очищает коллекцию
     */

    public String clear(User user){
        List<LabWork> userLabworks = getLabworksLogUser(user, collection);
        if (userLabworks.isEmpty()){
            return "У пользователя нет добавленных объектов!";
        }
        if (DBManager.clear(user.getUsername())){
            collection.removeAll(userLabworks);
            return "Объекты удалены!";
        }
        return "Ошибка с базой данных!";
    }

    public List<LabWork> getLabworksLogUser(User user, ArrayDeque<LabWork> userLabworks){
        List<LabWork> result = userLabworks.stream()
                .filter(labWork -> labWork.getUsername().equals(user.getUsername()))
                .collect(Collectors.toList());
        return  result;
    }

    /**
     * Метод проверяет есть ли элемент с данным id в коллекции
     * @param id
     * @return
     */
    public boolean containsCollection(int id, User user){
        if (id < 1){
            return false;
        }
        boolean result = collection.stream()
                .filter(labWork -> labWork.getUsername().equals(user.getUsername()))
                .anyMatch(val -> val.getId() == id);
        return result;
    }

    /**
     * Метод позволяет получить элемент коллекции по id
     * @param id
     * @return
     */
    public LabWork getLabworkById(int id){
        if (id<=0){
            System.out.println("Некорректный id!");
        } else {
            for (LabWork lab: collection){
                if (lab.getId() == id){
                    return lab;
                }
            }
        } return null;
    }

    /**
     * Метод удаляет элемент из базы данных и из коллекции по id
     * @param id
     * @return
     */
    public boolean removeId(int id){
        if (id < 1){
            return false;
        }
        if (DBManager.removeById(id)){
            collection.remove(getLabworkById(id));
            return true;
        }
        return false;
    }


    /**
     * Метод удаляет элемент из коллекции по id
      * @param id
     * @return
     */
    public boolean removeById(int id){
        if (id < 1){
            return false;
        }
        for (LabWork lab: collection){
            if(lab.getId() == id){
                collection.remove(lab);
                return true;
            }
        }
        return false;
    }

    public int getSize(){
        return collection.size();
    }

    /**
     * Метод сортировки коллекции
     */
    public void sortCollection(){
        this.collectionArr = new ArrayList<>(this.collection);
        collectionArr.sort(new Comparator<LabWork>() {
            @Override
            public int compare(LabWork o1, LabWork o2) {
                if (o1.getCoordinates().getX() + o1.getCoordinates().getY() == o2.getCoordinates().getX() +o2.getCoordinates().getY()) return 0;
                else if (o1.getCoordinates().getX() +o1.getCoordinates().getY() > o2.getCoordinates().getX() +o2.getCoordinates().getY()) return 1;
                else return -1;
            }
        });
        collection = new ArrayDeque<>(collectionArr);

    }

    /**
     * Метод нахождения максимального по минимальному баллу элемента
     * @return
     */
    public Double findMaxMinimalPoint(){
        Double maxPoint = 0.0;
        for (LabWork lab: collection){
            if (lab.getMinimalPoint() > maxPoint){
                maxPoint = lab.getMinimalPoint();
            }
        }
        return maxPoint;
    }
    /**
     * Метод нахождения минимального по весу элемента
     * @return
     */
    public long findMinimalX(){
        long minX = 9223372036854775807L;
        for (LabWork lab: collection){
            if ((lab.getCoordinates().getX() < minX)){
                minX = lab.getCoordinates().getX();
            }
        }
        return minX;
    }

    /**
     * Метод нахождения минимального минимального значения
     */
    public LabWork labMinimalPoint(){
        Double minPoint = 9223372036854775807.0;
        LabWork minlabWork = new LabWork();
        for (LabWork lab: collection){
            if (lab.getMinimalPoint() < minPoint){
                minPoint = lab.getMinimalPoint();
                minlabWork = lab;
            }
        } return (minlabWork);
    }

    /**
     * Метод вывода элементов с заданным полем минимального значения
     * @param minPoint
     */
    public String filterMinPoint(Double minPoint){
        return collection
                .stream()
                .filter((labWork -> labWork.getMinimalPoint().equals(minPoint)))
                .map(LabWork::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Метод вывода элементов, чье поле минимального значения больше заданного
     * @param minPoint
     */
    public String greaterLab(Double minPoint){
        return collection
                .stream()
                .filter((labWork -> labWork.getMinimalPoint() > minPoint))
                .map(LabWork::toString)
                .collect(Collectors.joining("\n"));

    }
}
