package com.elyseev.example.data


/**
 * Created by Elyseev Vladimir on 05.06.18.
 */
enum class SheetType {
    ALERT, ACTIONS, SINGLE, MULTIPLE, SEEK, EDIT, CUSTOM
}

enum class AlertType(val title: String) {
    ALERT("Default alert"),
    DIALOG("Default dialog"),
    CUSTOM_COLOR("Custom colors"),
}

enum class ActionsType(val title: String) {
    ACTIONS("Actions list"),
    ACTIONS_WITH_ICON("Actions list with icon"),
    ACTIONS_CUSTOM_COLORS("Actions list with customs colors")
}

enum class SingleType(val title: String) {
    SINGLE("Single list"),
    SINGLE_WITH_ICON("Single list with icons")
}

enum class MultipleType(val title: String) {
    MULTIPLE("Multiple list"), MULTIPLE_WITH_ICON("Multiple list with icons")
}

enum class SeekType(val title: String) {
    SEEK("Seek view")
}

enum class EditType(val title: String) {
    EDIT("Edit view")
}

enum class CustomType(val title: String) {
    CUSTOM("Custom view")
}