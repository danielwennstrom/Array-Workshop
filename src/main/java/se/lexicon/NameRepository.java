package se.lexicon;

import java.util.Arrays;

/**
 * The NameRepository class provides methods to manage a list of names.
 * It offers functionalities such as adding, removing, finding, and updating names.
 */
public class NameRepository {

    private static String[] names = new String[0]; // element format should be ["firstName lastName", "firstName lastName"]


    /**
     * Retrieves the current size of the names array.
     *
     * @return The number of elements in the names array.
     */
    public static int getSize() {
        //todo: PART 1: implement getSize method
        return NameRepository.names.length;
    }


    /**
     * Sets the names array to the provided array of names & it should replace all existing names.
     *
     * @param names The array of names to set.
     */
    public static void setNames(final String[] names) {
        //todo: PART 1: implement setNames method
        NameRepository.names = names;
    }


    /**
     * Clears the names array by creating a new empty array.
     */
    public static void clear() {
        //todo: PART 1: implement clear method
        NameRepository.names = new String[0];
    }


    /**
     * Returns all names in a new array (Retrieves a copy of the names array).
     *
     * @return A new array containing all elements from the names array.
     */
    public static String[] findAll() {
        //todo: PART 1: implement findAll method
        return NameRepository.names;
    }


    /**
     * Finds a name that matches the given fullName case-insensitively.
     *
     * @param fullName The full name to search for.
     * @return The matching name if found; otherwise, null.
     */
    public static String find(final String fullName) {
        //todo: PART 2: implement find method
        for (String name : NameRepository.names) {
            if (name.equalsIgnoreCase(fullName)) {
                return name;
            }
        }

        return null;
    }


    /**
     * Adds a new fullName to the names array if it doesn't already exist.
     *
     * @param fullName The full name to add.
     * @return True if the fullName is added successfully; false if it already exists.
     */
    public static boolean add(final String fullName) {
        //todo: PART 2: implement add method
        if (fullName == null) {
            throw new IllegalArgumentException("The full name cannot be null.");
        }

        int size = getSize();
        String[] names = NameRepository.names;

        for (int i = 0; i < size; i++) {
            if (names[i] != null && names[i].equalsIgnoreCase(fullName)) {
                return false;
            }
        }

        String[] newNames = Arrays.copyOf(names, size + 1);
        newNames[size] = fullName;

        setNames(newNames);

        return true;
    }


    /**
     * Find all names that match the given firstName.
     *
     * @param firstName The first name to search for.
     * @return An array containing all matching names.
     */
    public static String[] findByFirstName(final String firstName) {
        //todo: PART 3: findByFirstName method
        if (firstName == null) {
            throw new IllegalArgumentException("The first name cannot be null.");
        }

        String[] names = NameRepository.names;
        int size = getSize();
        String[] matchedNames = new String[size];
        int count = 0;

        for (String name : names) {
            if (name != null) {
                int spaceIndex = name.indexOf(" ");
                if (spaceIndex != -1) {
                    String subFirstName = name.substring(0, spaceIndex);

                    if (subFirstName.equalsIgnoreCase(firstName)) {
                        matchedNames[count] = name;
                        count++;
                    }
                }
            }
        }

        return Arrays.copyOf(matchedNames, count);
    }


    /**
     * Find all names that match the given lastName.
     *
     * @param lastName The last name to search for.
     * @return An array containing all matching names.
     */
    public static String[] findByLastName(final String lastName) {
        //todo: PART 3: implement findByLastName method
        if (lastName == null) {
            throw new IllegalArgumentException("The last name cannot be null.");
        }

        String[] names = NameRepository.names;
        int size = getSize();
        String[] matchedNames = new String[size];
        int count = 0;

        for (String name : names) {
            if (name != null) {
                int spaceIndex = name.indexOf(" ");
                if (spaceIndex != -1) {
                    String subLastName = name.substring(spaceIndex + 1);

                    if (subLastName.equalsIgnoreCase(lastName)) {
                        matchedNames[count] = name;
                        count++;
                    }
                }
            }
        }

        return Arrays.copyOf(matchedNames, count);
    }


    /**
     * Updates a name in the names array from the original name to the updated name.
     *
     * @param original    The original name to update.
     * @param updatedName The updated name to set.
     * @return True if the name is updated successfully; false if the updated name already exists or the original name is not found.
     */
    public static boolean update(final String original, final String updatedName) {
        //todo: PART 3: implement update method
        if (original == null || updatedName == null) {
            throw new IllegalArgumentException("The original and updated names cannot be null.");
        }

        String[] names = NameRepository.names;
        int size = getSize();
        int foundIndex = -1;

        for (int i = 0; i < size; i++) {
            if (names[i] != null) {
                if (names[i].equalsIgnoreCase(updatedName)) {
                    return false;
                } else if (names[i].equalsIgnoreCase(original)) {
                    foundIndex = i;
                }
            }
        }

        if (foundIndex != -1) {
            names[foundIndex] = updatedName;
            setNames(names);

            return true;
        }

        return false;
    }

    /**
     * Removes a name from the names array, case-insensitively.
     *
     * @param fullName The full name to remove.
     * @return True if the name is removed successfully; false if the name is not found in the array.
     */
    public static boolean remove(final String fullName) {
        //todo: PART 4: implement remove method
        if (fullName == null) {
            throw new IllegalArgumentException("The full name cannot be null.");
        }

        String[] names = NameRepository.names;
        int size = getSize();
        String[] newNames = new String[size - 1];
        int newIndex = 0;
        boolean wasFound = false;

        for (int i = 0; i < size; i++) {
            if (names[i] != null) {
                if (!wasFound && names[i].equalsIgnoreCase(fullName)) {
                    wasFound = true;
                } else if (newIndex < newNames.length){
                    newNames[newIndex++] = names[i];
                }
            }
        }

        if (wasFound) {
            setNames(Arrays.copyOf(newNames, newIndex));
        }

        return wasFound;
    }
}